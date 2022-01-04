package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AOCPuzzle {
	
	private int part = 1;
	public AOCPuzzle(String day)
	{
		File file = new File("C:/Users/jasmi/OneDrive/Desktop/AoC/Day9/input.txt");
		if(!file.exists())
		{
			solve(new ArrayList<>());
			return;
		}

		BufferedReader reader;
		try
		{
			reader = new BufferedReader(new FileReader(file));
		} catch(FileNotFoundException e)
		{
			System.err.println("File do not exist");
			solve(new ArrayList<>());
			return;
		}

		List<String> inputLines = new ArrayList<>();
		try
		{
			String line;
			while((line = reader.readLine()) != null)
				inputLines.add(line);

			reader.close();
		} 
		catch(IOException e){
			
			e.printStackTrace();
		}

		solve(inputLines);
	}

	abstract void solve(List<String> input);

	public void lap(long answer )
	{
		lap(String.valueOf(answer));
	}

	public void lap(String answer)
	{
		System.out.println("Part " + part + ": " + answer );
		part++;
	}


}
