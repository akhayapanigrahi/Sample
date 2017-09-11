package com.WhatFix.util;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelutilObject {

	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	private static XSSFCell Cell;
	private static XSSFRow Row;

public static void setExcelFile(String Path,String SheetName) throws Exception 
{

		try {

			// Open the Excel file

		FileInputStream ExcelFile = new FileInputStream(Path);

		// Access the required test data sheet

		ExcelWBook = new XSSFWorkbook(ExcelFile);

		ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} 
		catch (Exception e)
		{

			throw (e);

		}

}

public  static String getCellData(int RowNum, int ColNum) throws Exception{
	
	String CellData = null; 

		try{

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			 
			 
            if (Cell.getCellType()==XSSFCell.CELL_TYPE_STRING) {


                  CellData = Cell.getStringCellValue();


            }else if (Cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC) {

            	long i = (long) Cell.getNumericCellValue();

                CellData = new Long(i).toString();


            } 


			return CellData;

			}
		catch (Exception e)
		{

			return"";

			}

}

}