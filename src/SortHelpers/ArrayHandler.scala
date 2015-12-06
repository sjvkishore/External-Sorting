package SortHelpers

import FileHelpers.Storer

import  scala.util.Sorting

/**
 * Class that sorts an array of strings
 * and stores it into a given file
 *
 * @param itr the array to sort
 */
class ArrayHandler(val itr: Array[String]) extends Storer {

  /**
   * Method that quicksorts the given array and saves it into a file
   *
   * @return file name where the info is stored
   */
  def quickSort(): String = {
    // Scala provides us with a nice sorted function
    // conversely we could use as well QuickSort method
    // from utils.Sorting.quicksort but according to some
    // documentation there is no major difference in performance
    Sorting.quickSort(itr)
    saveToFile(itr)
  }

  /**
   * Method that sorts the given array and saves intoa fiel
   *
   * @return file name where the info is stored
   */
  def sort(): String = {
    // Scala provides us with a nice sorted function
    itr.sorted
    saveToFile(itr)
  }
}
