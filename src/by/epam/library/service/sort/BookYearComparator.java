package by.epam.library.service.sort;

import java.util.Comparator;
import java.util.Date;

import by.epam.library.entity.PrintedEdition;

public class BookYearComparator implements Comparator<PrintedEdition> {

	@Override
	public int compare(PrintedEdition obj1, PrintedEdition obj2) {
		int year1 = obj1.getPublishedYear().getYear();
		int year2 = obj2.getPublishedYear().getYear();

		if (year1 <= year2) {
			return -1;
		} else {
			return 1;
		}
	}
}