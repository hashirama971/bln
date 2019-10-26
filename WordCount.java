import java.io.IOException;
import java.util.StringTokenizer;
import java.util.function.LongToIntFunction;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {
	
	public static class Tokenizer extends Mapper<LongWritable, Text, Text, IntWritable>{

		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			StringTokenizer token = new StringTokenizer(value.toString());
			
			while(token.hasMoreTokens()) {
				context.write(new Text(token.nextToken()), new IntWritable(1));
			}
		
		}

	
		
	}
	
	public static class Reducer extends org.apache.hadoop.mapreduce.Reducer<Text, IntWritable, Text, IntWritable>{

		@Override
		protected void reduce(Text arg0, Iterable<IntWritable> arg1,
				org.apache.hadoop.mapreduce.Reducer<Text, IntWritable, Text, IntWritable>.Context arg2)
				throws IOException, InterruptedException {
				int sum=0;
				for(IntWritable it : arg1) {
					sum=sum+it.get();
				}
				arg2.write(arg0,new IntWritable(sum));
		}
		
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf,"workcount");
		job.setJarByClass(WordCount.class);
		job.setMapperClass(Tokenizer.class);
		job.setCombinerClass(Reducer.class);
		job.setReducerClass(Reducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true)?0:1);
		
		
		
		
		
	}
}
