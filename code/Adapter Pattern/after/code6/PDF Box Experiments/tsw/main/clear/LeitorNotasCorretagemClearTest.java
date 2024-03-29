package main.clear;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import main.utils.FilesFoldersUtil;

class LeitorNotasCorretagemClearTest {

	private static final String PATH__SRC_MAIN_RESOURCES = "src\\main\\resources\\";

	@Test
	void testGetRelativePath_SrcMainResources_NotNull() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		String path = leitorNotasClear.getRelativePath_SrcMainResources();
		
		// Assert
		assertNotNull(path);
	}
	
	@Test
	void testGetRelativePath_SrcMainResources_NotEmpty() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		String path = leitorNotasClear.getRelativePath_SrcMainResources();
		
		// Assert
		assertFalse(path.isEmpty());
	}
	
	@Test
	void testGetRelativePath_SrcMainResources_ContainsSrcMainResources() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		String path = leitorNotasClear.getRelativePath_SrcMainResources();
		
		// Assert
		assertTrue(path.endsWith(PATH__SRC_MAIN_RESOURCES));
	}
	
	@Disabled
	@Test
	void testGetFile() {
		// Arrange
		
		
		// Act
		
		
		// Assert
		
		fail("Not yet implemented");
	}
	
	@Test
	void testGetPdfDocument_FirstFile_NotNull() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		PDDocument result= leitorNotasClear.getPdfDocument();
		
		// Assert
		assertNotNull(result);
	}
	
	@Test
	void testGetPdfDocument_FirstFile_PageQuantityGreaterThanZero() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		int result= leitorNotasClear.getPdfDocument().getNumberOfPages();
		
		// Assert
		assertThat(result).isGreaterThan(0);
	}
	
	@Ignore
	@Disabled
	@Test
	void test_readAllContentFrom_PdfDocument_NotNull() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		String response = 
			leitorNotasClear.readAllContentFrom(
			leitorNotasClear.getPdfDocument()
		);
		
		// Assert
		assertThat(response).isNotNull();
	}
	
	@Ignore
	@Disabled
	@Test
	void test_readAllContentFrom_PdfDocument_NotEmpty() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		String response =
			leitorNotasClear.readAllContentFrom(
			leitorNotasClear.getPdfDocument()
		);
		
		// Assert
		assertThat(response).isNotEmpty();
		
		System.out.println(response);
	}
	
	@Disabled
	@Test
	void testReadLine() {
		// Arrange
		
		
		// Act
		
		
		// Assert
		
		fail("Not yet implemented");
	}
	
	@Test
	void testGetAllFilesInDirectory_NotNull() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		List<File> result = leitorNotasClear.getAllFilesInDirectory();
		
		// Assert
		assertNotNull(result);
	}
	
	@Test
	void testGetAllFilesInDirectory_NotEmpty() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		List<File> result = leitorNotasClear.getAllFilesInDirectory();
		
		// Assert
		assertFalse(result.isEmpty());
	}
	
	@Test
	void testGetAllFilesInDirectory_OnlyFilesOnList() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		List<File> result = leitorNotasClear.getAllFilesInDirectory();
		
		// Assert
		for (File file : result) {
			if ( !file.isFile() ) {
				fail("There is a Non File in the List");
			}
		}
	}
	
	@Test
	void test_getAllPDF_FilesInDirectory_AllFilesArePDFs() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		List<File> result = leitorNotasClear.getAllPDF_FilesInDirectory();
		
		// Assert
		for (File file : result) {
			boolean isPDF = FilesFoldersUtil.isPDF(file);
			
			if ( !isPDF ) {
				fail("There is a Non PDF File in the List");
			}
		}
	}
	
	@Test
	void test_readPage_FirstPageContent_NotNull() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument pdfDocument = leitorNotasClear.getPdfDocument();
		int firstPageNumber = 0;
		
		// Act
		String response = leitorNotasClear.readPage(pdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isNotNull();
	}
	
	@Test
	void test_readPage_FirstPageContent_NotEmpty() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument pdfDocument = leitorNotasClear.getPdfDocument();
		int firstPageNumber = 0;
		
		// Act
		String response = leitorNotasClear.readPage(pdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isNotEmpty();
	}
	
	@Test
	void test_readDate_FirstPage_NotNull() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument pdfDocument = leitorNotasClear.getPdfDocument();
		int firstPageNumber = 0;
		
		// Act
		String response = leitorNotasClear.readDate(pdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isNotNull();
	}
	
	@Test
	void test_readDate_FirstPage_NotEmpty() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument pdfDocument = leitorNotasClear.getPdfDocument();
		int firstPageNumber = 0;
		
		// Act
		String response = leitorNotasClear.readDate(pdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isNotEmpty();
	}
	
	@Test
	void test_readDate_FirstPage_13July2018() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument pdfDocument = leitorNotasClear.getPdfDocument();
		int firstPageNumber = 0;
		
		// Act
		String response = leitorNotasClear.readDate(pdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isEqualTo("13/07/2018");
	}
	
	@Test
	void test_readDate_SecondtPage_20July2018() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument pdfDocument = leitorNotasClear.getPdfDocument();
		int firstPageNumber = 1;
		
		// Act
		String response = leitorNotasClear.readDate(pdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isEqualTo("20/07/2018");
	}
	
}
