package uts;

import java.util.Scanner;

public class UnaryStack {
	String entry;
	int count = 0;
	int realCount = 0;
	boolean isLeft = true;
	char[] stack = new char[100];
	String[] outputHelper = new String [10];
	
	public void validate() {
		String clearEntry = entry.replaceAll("\\s", "");
        if (isValidEntry(clearEntry.toCharArray())) {
            process(clearEntry.toCharArray());
        }
	}
	
	public static boolean isValidEntry(char[] entry) {
        int equalsCount = 0;
        for (char c : entry) {
            if (c == '=') {
                equalsCount++;
            }

            if (!(c == '1' || c == '+' || c == '=')) {
                System.out.println("Tidak valid karena karakter yang diijinkan hanya 1, +, dan =.");
                return false;
            }
        }
        if (equalsCount != 1) {
            System.out.println("Tidak valid karena hanya satu tanda sama dengan yang diijinkan.");
            return false;
        }
        return true;
    }
	
	public void process (char[] entryInCharArray) {
		int unaryCount = 0;
		int operatorCount = 0;
		
		for (int i = 0; i < entryInCharArray.length; i++) {
			char item = entryInCharArray[i];
			
		if (item == '=') {
			isLeft = false;
		}
		
		if (isLeft) {
			if (item == '1') {
				push(item);
				unaryCount++;
			}
		} else {
			if (item == '1' ) {
				pop();
				unaryCount++;
			}
		}
		
		if (item != '1' || i == entryInCharArray.length - 1) {
            if (operatorCount == 0) {
            	outputHelper[operatorCount] = String.valueOf(unaryCount);
            } else {
            	outputHelper[operatorCount] = String.valueOf(unaryCount - sum(operatorCount - 2));
            }
            operatorCount++;
            
            if (i != entryInCharArray.length - 1) {
            	outputHelper[operatorCount] = String.valueOf(item);
            	operatorCount++;
            }
		}
	}
	
	print(realCount == 0);
	

}
	public int sum(int index) {
        int sum = 0;
        for (int i = index; i >= 0; i = i - 2) {
            sum = sum + Integer.parseInt(outputHelper[i]);
        }
        return sum;
    }
    public void print(boolean isValid) {
        if (isValid) {
            System.out.print(entry + " adalah persamaan yang valid karena ");
            for (String s : outputHelper) {
                if (s != null) {
                    System.out.print(s + " ");
                }
            }
        } else {
            System.out.print(entry + " tidak valid karena ");
            for (String s : outputHelper) {
                if (s != null) {
                    if (s.equals("=")) {
                        System.out.print("tidak sama dengan ");
                    } else {
                        System.out.print(s + " ");
                    }
                }
            }
        }
    }
    public void push(char item) {
        if (count != stack.length) {
            stack[count] = item;
            count++;
            realCount++;
        }
    }
    public void pop() {
        // memastikan stack tidak 0
        if (count != 0) {
            --count;
        }
        
        
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UnaryStack stack = new UnaryStack();

        stack.entry = scanner.nextLine();
        stack.validate();
    }
}
