package by.epam.library.main;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import by.epam.library.entity.Book;
import by.epam.library.entity.Library;
import by.epam.library.entity.Magazine;
import by.epam.library.entity.PrintedEdition;
import by.epam.library.service.LibraryService;
import by.epam.library.service.find.FindByAuthor;
import by.epam.library.service.find.FindByPrice;
import by.epam.library.service.find.Findable;
import by.epam.library.service.sort.BookPriceComparator;
import by.epam.library.service.sort.BookYearComparator;
import by.epam.library.view.PrintAsList;
import by.epam.library.view.PrintAsTable;
import by.epam.library.view.Printable;
import by.epam.library.view.ViewAction;

public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Library myLibrary = new Library();

		myLibrary.add(new Book("Head First Java, 2nd Edition", 300,
				"Kathy Sierra", new Date(2004,04,02)));
		myLibrary.add(new Book("Head First Java", 300, "Kathy Sierra", new Date(1987,04,02)));
		myLibrary.add(new Book("Head First Java, 3d Edition", 150,
				"Kathy Sierra", new Date(2005,04,02)));
		myLibrary.add(new Book("Head First Java, 4th Edition", 450,
				"Kathy Sierra", new Date(2001,05,02)));
		myLibrary.add(new Book("Head First PHP", 50, "Kathy Sierra", new Date(2018,04,02)));
		myLibrary.add(new Book("C#", 50, "Kathy Sierra", new Date(2006,04,03)));
		myLibrary.add(new Book("C# In depth", 300, "Skeet J.", new Date(1999,04,03)));
		myLibrary.add(new Book("The Design and Evolution of C++", 250,
				"Stroustrup B.", new Date(2004,04,02)));
		myLibrary.add(new Magazine("Cosmopolitan", 40, new Date(2018,04,02), 50));
		myLibrary.add(new Magazine("Men'sHealth", 300, new Date(2017,04,02), 50));
		myLibrary.add(new Book("Head First Java, 1st Edition", 300,
				"Kathy Sierra", new Date(2005,04,02)));
		myLibrary.add(new Book("PHP Objects, Patterns, and Practice", 300,
				"Zandstra M.",new Date(2018,04,02)));

		myLibrary.remove(new Book("Head First Java", 300, "Kathy Sierra",new Date(1987,04,02)));

		
		Findable matcher = new FindByPrice(300);
		LibraryService libService = new LibraryService();
		List<PrintedEdition> lists = libService.find(myLibrary, matcher);

		System.out.println("O! We've found " + lists.size()
				+ " printed edition! \n");

		ViewAction.print(new PrintAsList(), lists);
		ViewAction.print(new PrintAsTable(), lists);

		
		//Find book by Author, as example by Kathy Sierra
		Findable matcher2 = new FindByAuthor("Kathy Sierra");

		List<PrintedEdition> lists2 = libService.find(myLibrary, matcher2);
		System.out.println("\n");
		System.out.println("O! We've found " + lists2.size()
				+ " printed edition written by Kathy Sierra! \n");

		//Sort found books by price
		System.out.println("****************************************");
		System.out.println("List of found books sorted by price:");
		System.out.println("****************************************");
		Comparator<PrintedEdition> comparatorByPrice = new BookPriceComparator();

		Set<PrintedEdition> bookSortedByPrice = new TreeSet<PrintedEdition>(comparatorByPrice);
		for (int i = 0; i < lists2.size(); i++) {
			bookSortedByPrice.add(lists2.get(i));
		}
		for (PrintedEdition elements : bookSortedByPrice) {
			System.out.print(elements.toString() + "\n");
		}
		
		//Sort found books by year
		System.out.println("");
		System.out.println("*********************************************");
		System.out.println("List of found books sorted by published year:");
		System.out.println("*********************************************");

		Comparator<PrintedEdition> comparatorByYear = new BookYearComparator();

		Set<PrintedEdition> bookSortedByYear = new TreeSet<PrintedEdition>(comparatorByYear);
		for (int i = 0; i < lists2.size(); i++) {
			bookSortedByYear.add(lists2.get(i));
		}
		for (PrintedEdition elements : bookSortedByYear) {
			System.out.print(elements.toString() + "\n");
		}
	}

}
