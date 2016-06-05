/*
 *  Copyright 2004 Clinton Begin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.oltranz.IntercityTransport.library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Implementation of PaginatedList backed by an ArrayList
 * 
 */
public class PaginatedArrayList implements PaginatedList {

  private static final ArrayList EMPTY_LIST = new ArrayList(0);

  private List list;

  private List page;

  private int pageSize;

  private int index;

  /**
   * @param pageSize
   */
  public PaginatedArrayList(int pageSize) {
    this.pageSize = pageSize;
    this.index = 0;
    this.list = new ArrayList();
    repaginate();
  }

  /**
   * Constructor to set the initial size and the page size
   * 
   * @param initialCapacity -
   *          the initial size
   * @param pageSize -
   *          the page size
   */
  public PaginatedArrayList(int initialCapacity, int pageSize) {
    this.pageSize = pageSize;
    this.index = 0;
    this.list = new ArrayList(initialCapacity);
    repaginate();
  }

  /**
   * Constructor to create an instance using an existing collection
   * 
   * @param c -
   *          the collection to build the instance with
   * @param pageSize -
   *          the page size
   */
  public PaginatedArrayList(Collection c, int pageSize) {
    this.pageSize = pageSize;
    this.index = 0;
    this.list = new ArrayList(c);
    repaginate();
  }

  private void repaginate() {
    if (list.isEmpty()) {
      page = EMPTY_LIST;
    } else {
      int start = index * pageSize;
      int end = start + pageSize - 1;
      if (end >= list.size()) {
        end = list.size() - 1;
      }
      if (start >= list.size()) {
        index = 0;
        repaginate();
      } else if (start < 0) {
        index = list.size() / pageSize;
        if (list.size() % pageSize == 0) {
          index--;
        }
        repaginate();
      } else {
        page = list.subList(start, end + 1);
      }
    }
  }

  /* List accessors (uses page) */
@Override
  public int size() {
    return page.size();
  }

 @Override
  public boolean isEmpty() {
    return page.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return page.contains(o);
  }

  @Override
  public Iterator iterator() {
    return page.iterator();
  }

  @Override
  public Object[] toArray() {
    return page.toArray();
  }

  @Override
  public Object[] toArray(Object a[]) {
    return page.toArray(a);
  }

  @Override
  public boolean containsAll(Collection c) {
    return page.containsAll(c);
  }

  @Override
  public Object get(int index) {
    return page.get(index);
  }

  @Override
  public int indexOf(Object o) {
    return page.indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return page.lastIndexOf(o);
  }

  @Override
  public ListIterator listIterator() {
    return page.listIterator();
  }

  @Override
  public ListIterator listIterator(int index) {
    return page.listIterator(index);
  }

  @Override
  public List subList(int fromIndex, int toIndex) {
    return page.subList(fromIndex, toIndex);
  }

  /* List mutators (uses master list) */

  @Override
  public boolean add(Object o) {
    boolean b = list.add(o);
    repaginate();
    return b;
  }

  @Override
  public boolean remove(Object o) {
    boolean b = list.remove(o);
    repaginate();
    return b;
  }

  @Override
  public boolean addAll(Collection c) {
    boolean b = list.addAll(c);
    repaginate();
    return b;
  }

  @Override
  public boolean addAll(int index, Collection c) {
    boolean b = list.addAll(index, c);
    repaginate();
    return b;
  }

  @Override
  public boolean removeAll(Collection c) {
    boolean b = list.removeAll(c);
    repaginate();
    return b;
  }

  @Override
  public boolean retainAll(Collection c) {
    boolean b = list.retainAll(c);
    repaginate();
    return b;
  }

  @Override
  public void clear() {
    list.clear();
    repaginate();
  }

  @Override
  public Object set(int index, Object element) {
    Object o = list.set(index, element);
    repaginate();
    return o;
  }

  @Override
  public void add(int index, Object element) {
    list.add(index, element);
    repaginate();
  }

  @Override
  public Object remove(int index) {
    Object o = list.remove(index);
    repaginate();
    return o;
  }

  /* Paginated List methods */

  @Override
  public int getPageSize() {
    return pageSize;
  }

  @Override
  public boolean isFirstPage() {
    return index == 0;
  }

  @Override
  public boolean isMiddlePage() {
    return !(isFirstPage() || isLastPage());
  }

  @Override
  public boolean isLastPage() {
    return list.size() - ((index + 1) * pageSize) < 1;
  }

  @Override
  public boolean isNextPageAvailable() {
    return !isLastPage();
  }

  @Override
  public boolean isPreviousPageAvailable() {
    return !isFirstPage();
  }

  
  @Override
  public boolean nextPage() {
    if (isNextPageAvailable()) {
      index++;
      repaginate();
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean previousPage() {
    if (isPreviousPageAvailable()) {
      index--;
      repaginate();
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void gotoPage(int pageNumber) {
    index = pageNumber;
    repaginate();
  }

  @Override
  public int getPageIndex() {
    return index;
  }

}

/*
 * Copyright 2004 Clinton Begin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

/**
 * Interface for lists that support paging
 * 
 */
interface PaginatedList extends List {

  /**
   * Returns the maximum number of items per page
   * 
   * @return The maximum number of items per page.
   */
  public int getPageSize();

  /**
   * Is the current page the first page?
   * 
   * @return True if the current page is the first page or if only a single page
   *         exists.
   */
  public boolean isFirstPage();

  /**
   * Is the current page a middle page (ie not first or last)?
   * 
   * @return True if the current page is not the first or last page, and more
   *         than one page exists (always returns false if only a single page
   *         exists).
   */
  public boolean isMiddlePage();

  /**
   * Is the current page the last page?
   * 
   * @return True if the current page is the last page or if only a single page
   *         exists.
   */
  public boolean isLastPage();

  /**
   * Is a page available after the current page?
   * 
   * @return True if the next page is available
   */
  public boolean isNextPageAvailable();

  /**
   * Is a page available before the current page?
   * 
   * @return True if the previous page is available
   */
  public boolean isPreviousPageAvailable();

  /**
   * Moves to the next page after the current page. If the current page is the
   * last page, wrap to the first page.
   * 
   * @return True if the page changed
   */
  public boolean nextPage();

  /**
   * Moves to the page before the current page. If the current page is the first
   * page, wrap to the last page.
   * 
   * @return True if the page changed
   */
  public boolean previousPage();

  /**
   * Moves to a specified page. If the specified page is beyond the last page,
   * wrap to the first page. If the specified page is before the first page,
   * wrap to the last page.
   * 
   * @param pageNumber
   *          The page to go to
   */
  public void gotoPage(int pageNumber);

  /**
   * Returns the current page index, which is a zero based integer. All
   * paginated list implementations should know what index they are on, even if
   * they don't know the ultimate boundaries (min/max).
   * 
   * @return The current page
   */
  public int getPageIndex();

}
