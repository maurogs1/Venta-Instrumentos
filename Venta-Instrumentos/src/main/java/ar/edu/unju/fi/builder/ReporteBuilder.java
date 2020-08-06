package ar.edu.unju.fi.builder;
import javax.inject.Named;

//Abstract Builder
@Named
public abstract class ReporteBuilder {
	
	public abstract void createDocument();
	public abstract void setDocument();
	public abstract void setTable();
	public abstract void saveDocument();
	
	
}
