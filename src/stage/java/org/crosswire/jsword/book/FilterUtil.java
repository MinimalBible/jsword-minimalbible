package org.crosswire.jsword.book;

import org.crosswire.jsword.book.BookCategory;
import org.crosswire.jsword.book.BookFilter;
import org.crosswire.jsword.book.BookFilters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Module to help with some conversions between BookCategories and BookFilters
 * Makes more sense to move it to JSword than incorporate in MinimalBible
 */
public class FilterUtil {

    private static final BookCategory[] MAPPABLE_CATEGORIES = {BookCategory.BIBLE,
            BookCategory.COMMENTARY, BookCategory.DAILY_DEVOTIONS, BookCategory.DICTIONARY,
            BookCategory.GENERAL_BOOK, BookCategory.GLOSSARY, BookCategory.MAPS,
            BookCategory.OTHER
    };
    private static final BookFilter[] MAPPABLE_FILTERS = {BookFilters.getBibles(),
            BookFilters.getCommentaries(), BookFilters.getDailyDevotionals(), BookFilters.getDictionaries(),
            BookFilters.getGeneralBooks(), BookFilters.getGlossaries(), BookFilters.getMaps(),
            BookFilters.getNonBibles()
    };

    public static BookCategory categoryFromFilter(BookFilter f)
            throws InvalidFilterCategoryMappingException {
        int index = Arrays.asList(MAPPABLE_FILTERS).indexOf(f);
        if (index != -1) {
            return MAPPABLE_CATEGORIES[index];
        } else {
            throw new InvalidFilterCategoryMappingException("Can not map from filter: " + f.toString() + " to category.");
        }
    }

    public static BookFilter filterFromCategory(BookCategory c)
            throws InvalidFilterCategoryMappingException {
        int index = Arrays.asList(MAPPABLE_CATEGORIES).indexOf(c);
        if (index != -1) {
            return MAPPABLE_FILTERS[index];
        } else {
            throw new InvalidFilterCategoryMappingException("Can not map from category: " + c.toString() + " to filter.");
        }
    }

    public static List<Book> applyFilter(List<Book> books, BookFilter f) {
        List<Book> filtered = new ArrayList<Book>();
        for (Book b: books) {
            if (f.test(b)) {
                filtered.add(b);
            }
        }
        return filtered;
    }

    public static class InvalidFilterCategoryMappingException extends Exception {
        public InvalidFilterCategoryMappingException(String message) {
            super(message);
        }
    }
}