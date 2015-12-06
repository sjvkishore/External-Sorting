package FileHelpers

import SortHelpers.ArrayHandler

import java.io.{FileNotFoundException, IOException}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.util.control.Breaks

/**
 * Class that handles the file partition
 *
 * @param file to sort
 */
class FileSplitter(file:String) {

  private var fileIterator: Iterator[String] = _

  /**
    Max bunch of available memory
    for processing. It's an arbitrary number for
    easiness, real scenario would require a more
    thoughtful calculation based on available memory
   */
  private val maxRowsToSort: Int = 200000

  /**
   * List for storing the filenames of
   * smaller files that created by the algorithm
   */
  val listOfFiles: ArrayBuffer[String] = new ArrayBuffer[String]()

  /**
   * Function that initalizes the iterator
   */
  private def open(): Unit = {
    try {
      fileIterator = Source.fromFile(file).getLines()
    } catch {
      case e: FileNotFoundException => println(s"File $file not found")
      case e: IOException => println(s"File $file caused an IOException")
    }
  }

  /**
   * Processes the large file with the
   * algorithm, splitting the file into smaller files
   * that will contain a subset of the contents of the larger
   * file, ordering them in memory and then merge them back together
   */
  def process(): Unit = {
    open()
    if(Option(fileIterator).isDefined) {
      var rows: Array[String] = _
      while (fileIterator.hasNext) {
        for (a <-  0 to maxRowsToSort-1) {
          rows = new Array[String](maxRowsToSort)
          rows.update(a, fileIterator.next())
          if (fileIterator.isEmpty) { Breaks.break() }
        }
        val arrayHandler = new ArrayHandler(rows)
        listOfFiles.append(arrayHandler.quickSort())
      }
    } else {
      throw new Exception(s"Content from $file not loaded")
    }
  }
}