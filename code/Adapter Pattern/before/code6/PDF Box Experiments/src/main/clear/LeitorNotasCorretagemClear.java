package main.clear;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import main.LeitorPDF;
import main.utils.FilesFoldersUtil;


public class LeitorNotasCorretagemClear implements ILeitorNotasCorretagemClear {
	
	public static final String PAGE_HEADER	=	"NOTA DE CORRETAGEM";
	
	public static final String CONTENT_HEADER = "Q D/CValor Opera��o / AjustePre�o / AjusteQuantidadeObs. (*)Especifica��o do t�tuloPrazoTipo mercadoC/VNegocia��o";
	public static final String CONTENT_FOOTER = "Resumo dos Neg�cios Resumo Financeiro D/C\r\n";

	@Override
	public String getRelativePath_SrcMainResources() {
		// TODO Auto-generated method stub
		
		return FilesFoldersUtil.getFullPathToSrcMainResourceFolder();
	}

	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PDDocument getPdfDocument() {
		PDDocument response = new PDDocument();
		
		try {
			response = LeitorPDF.getPdDocument(getPathToFirstPdfFile());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * This code below is preventing to read the Content of PDF Document
		 * 
		 * finally { try { response.close(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } }
		 */
		
		return response;
	}

	@Override
	public String readLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getDirectory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> getAllFilesInDirectory() {
		List<File> response = 
			FilesFoldersUtil.getAllFiles(
			FilesFoldersUtil.getFullPathToSrcMainResourceFolder()
		);
		
		return response;
	}

	@Override
	public List<File> getAllPDF_FilesInDirectory() {
		List<File> allFiles = getAllFilesInDirectory();
		
		List<File> response = new ArrayList<File>();
		
		for (File file : allFiles) {
			String fileExtension = FilesFoldersUtil.getFileExtension(file);
			
			if ( FilesFoldersUtil.isPDF(fileExtension) ) {
				response.add(file);
			}
		}
		
		return response;
	}
	
	public static String	getPathToFirstPdfFile() {
		String response = "";
		
		LeitorNotasCorretagemClear leitorNotasCorretagemClear =
			new LeitorNotasCorretagemClear();
		
		List<File> allPdfFiles = leitorNotasCorretagemClear.getAllPDF_FilesInDirectory();
		
		if ( allPdfFiles != null && !allPdfFiles.isEmpty() ) {
			response = allPdfFiles.get(0).getAbsolutePath();
		}
		
		return response;
	}

	@Override
	public String readAllContentFrom(PDDocument pPdfDocument) {
		String response = "";
		
		LeitorNotasCorretagemClear leitorNotasCorretagemClear =
			new LeitorNotasCorretagemClear();
		
		try {
			response = LeitorPDF.getTexto( leitorNotasCorretagemClear.getPdfDocument() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}

	@Override
	public PDPage getPage(int pPageNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readPage(PDDocument pPdfDocument, int pPageNumer) {
		return LeitorPDF.getText(pPdfDocument, pPageNumer);
	}

	@Override
	public String readDate(PDDocument pPdfDocument, int pPageNumer) {
		String response = "";
		
		String pageContent = LeitorPDF.getText(pPdfDocument, pPageNumer);
		
		if ( pageContent != null && !pageContent.isEmpty() ) {
			List<String>	lines =  Arrays.asList(pageContent.split("\n"));
			
			if ( lines != null && !lines.isEmpty() ) {
				response = lines.get(2);
				
				if ( response != null && response.length() > 10 ) {
					response = response.substring(0, 10);
				}
			}
		}
		
		return response;
	}
	

}