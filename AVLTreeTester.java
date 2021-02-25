package edu.utdallas.oar180004.adt.tree.avl;

import java.util.Random;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

final class AVLTreeTester
{
	public static final void main(String[] args)
	{
		BufferedReader input1 = null;
		BufferedReader input2 = null;
		FileReader infile1 = null;
		FileReader infile2 = null;
		BufferedWriter output1 = null;
		BufferedWriter output2 = null;
		FileWriter outfile1 = null;
		FileWriter outfile2 = null;
		try
		{
			Random random1 = new Random();
			StringBuilder builder1 = new StringBuilder();
			for(int i = 0 ; i < 16 ; i++)
			{
				for(int j = 0 ; j < 3 ; j++)
				{
					builder1.append(Character.getNumericValue('0') + ((int) (random1.nextDouble() * (Character.getNumericValue('9') - Character.getNumericValue('0') + 1))));
				}
				builder1.append("-");
				for(int j = 4 ; j < 14 ; j++)
				{
					builder1.append(Character.getNumericValue('0') + ((int) (random1.nextDouble() * (Character.getNumericValue('9') - Character.getNumericValue('0') + 1))));
				}
				builder1.append(" Introduction_to_Algorithms" + i + " Cormen" + i + "\n");
			}
			outfile1 = new FileWriter("part1.dat");
			output1 = new BufferedWriter(outfile1);
			output1.write(builder1.toString());
			AVLTree<String, String> tree1 = new AVLTree<String, String>();
			String currentLine1 = "";
			String values1[] = new String[2];
			infile1 = new FileReader("part1.dat");
			input1 = new BufferedReader(infile1);
			while(input1 != null && input1.ready() && (currentLine1 = input1.readLine()) != null)
			{
				values1 = currentLine1.split(" ");
				tree1.insert(values1[0], values1[1]);
			}
			//infile1.close();
			//infile1 = null;
			//input1.close();
			//input1 = null;
			System.out.println("In-Order AVLTree - Part 1\n" + tree1 + "\n\n\n");
			Random random2 = new Random();
			StringBuilder builder2 = new StringBuilder();
			for(int i = 0 ; i < 16 ; i++)
			{
				for(int j = 0 ; j < 3 ; j++)
				{
					builder2.append(Character.getNumericValue('0') + ((int) (random2.nextDouble() * (Character.getNumericValue('9') - Character.getNumericValue('0') + 1))));
				}
				builder2.append("-");
				for(int j = 4 ; j < 14 ; j++)
				{
					builder2.append(Character.getNumericValue('0') + ((int) (random2.nextDouble() * (Character.getNumericValue('9') - Character.getNumericValue('0') + 1))));
				}
				builder2.append(" Introduction_to_Algorithms" + i + " Cormen" + i + "\n");
			}
			outfile2 = new FileWriter("part1.dat");
			output2 = new BufferedWriter(outfile2);
			output2.write(builder2.toString());
			AVLTree<String, String> tree2 = new AVLTree<String, String>();
			String currentLine2 = "";
			String values2[] = new String[2];
			infile2 = new FileReader("part2.dat");
			input2 = new BufferedReader(infile2);
			while(input2 != null && input2.ready() && (currentLine2 = input2.readLine()) != null)
			{
				values2 = currentLine2.split(" ");
				tree2.insert(values2[0], values2[1]);
			}
		}
		catch(IOException e)
		{
			System.out.println("Encountered an unhandled IOException, program will now exit.");
			System.out.println("WARNING: Data files may not have been closed properly!");
			e.printStackTrace(System.out);
		}
		System.out.println("Process completed.");
		System.exit(0);
	}
}

