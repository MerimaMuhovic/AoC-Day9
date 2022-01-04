package day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Day09 extends AOCPuzzle {

	public Day09() {
		super("9");
		
	}

	@Override
	void solve(List<String> input) {
		
		int[][] puzzle = new int[input.size()][input.get(0).length()];

		for(int row = 0; row < puzzle.length; row++)
			
		{
			String s = input.get(row).strip();
			
			for(int column = 0; column < puzzle[row].length; column++)
				
				puzzle[row][column] = Integer.parseInt(s.substring(column, column + 1));
		}

		List<Point> lows = new ArrayList<>();
		
		/*****  PART 1  ******/
		
		int sum = 0;
		for(int row = 0; row < puzzle.length; row++)
		{
			for(int column = 0; column < puzzle[row].length; column++)
			{
				int temp = puzzle[row][column];
				
				if(row - 1 >= 0 && temp >= puzzle[row - 1][column])
					continue;
				
				if(row + 1 < puzzle.length && temp >= puzzle[row + 1][column])
					continue;
				
				if(column - 1 >= 0 && temp >= puzzle[row][column - 1])
					continue;
				
				if(column + 1 < puzzle[row].length && temp >= puzzle[row][column + 1])
					continue;

				lows.add(new Point(row, column));
				
				sum += temp + 1;
			}
		}

		lap(sum);

		/*****  PART 2  ******/
		
		List<Long> highest = new ArrayList<>();
		
		for(Point p : lows){
			
			List<Point> visited = new ArrayList<>();
			List<Point> toVisit = new ArrayList<>();
			toVisit.add(p);
			
			while(toVisit.size() > 0){
				
				Point nextPoint = toVisit.remove(0);
				
				if(visited.contains(nextPoint))
					continue;

				visited.add(nextPoint);
				int row = nextPoint.row;
				int col = nextPoint.column;
				
				if(row - 1 >= 0 && puzzle[row - 1][col] != 9){
					
					Point down = new Point(row - 1, col);
					if(!visited.contains(down))
						toVisit.add(down);
				}
				if(row + 1 < puzzle.length && puzzle[row + 1][col] != 9){
					
					Point up = new Point(row + 1, col);
					if(!visited.contains(up))
						toVisit.add(up);
				}
				if(col - 1 >= 0 && puzzle[row][col - 1] != 9){
					
					Point left = new Point(row, col - 1);
					if(!visited.contains(left))
						toVisit.add(left);
				}
				if(col + 1 < puzzle[row].length && puzzle[row][col + 1] != 9){
					
					Point right = new Point(row, col + 1);
					if(!visited.contains(right))
						toVisit.add(right);
				}
			}

			if(highest.size() < 3){
				
				highest.add((long) visited.size());
			}
			else{
				
				if(highest.get(2) < (long) visited.size())
					highest.set(2, (long) visited.size());
			}
			highest.sort(Long::compare);
			highest.sort(Collections.reverseOrder());
		}

		lap(highest.get(0) * highest.get(1) * highest.get(2));
	}
	
	public class Point {
		
		public int row;
		public int column;

		public Point(int row, int column){
			
			this.row = row;
			this.column = column;
		}

		@Override
		public boolean equals(Object o){
			
			if(this == o) return true;
			if(o == null || getClass() != o.getClass()) return false;
			Point point = (Point) o;
			return row == point.row && column == point.column;
		}

		@Override
		public int hashCode(){
			
			return Objects.hash(row, column);
		}

	}
}

