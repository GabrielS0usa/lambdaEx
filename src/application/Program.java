package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Ënter full file path: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Employee> employee = new ArrayList<>();

			String line = br.readLine();

			while (line != null) {
				String[] vet = line.split(",");
				employee.add(new Employee(vet[0], vet[1], Double.parseDouble(vet[2])));
				line = br.readLine();
			}

			System.out.print("Enter salary: ");
			Double salary = sc.nextDouble();

			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

			List<String> emails = employee.stream().filter(e -> e.getSalary() > salary).map(e -> e.getEmail())
					.sorted(comp).collect(Collectors.toList());
			
			emails.forEach(System.out::println);
			
			Double sumSalarys = employee.stream().filter(e -> e.getName().charAt(0) == 'M').map(e -> e.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			
			System.out.printf("Sum of salary of people whose name starts with 'M': %.2f", sumSalarys);

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			sc.close();
		}

	}
}