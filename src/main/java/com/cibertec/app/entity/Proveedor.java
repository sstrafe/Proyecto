package com.cibertec.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proveedor")
public class Proveedor {

	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "idprov")
    private int idprov;
    
    @Column(name = "nomprov")
    private String nomprov;
    
    @Column(name = "tfnprov")
    private String tfnprov;
    
    @Column(name = "dirprov")
    private String dirprov;
    
    
	
 public Proveedor() {
    	
    }
	public Proveedor(int idprov, String nomprov, String tfnprov, String dirprov) {
		super();
		this.idprov = idprov;
		this.nomprov = nomprov;
		this.tfnprov = tfnprov;
		this.dirprov = dirprov;
	}




	public int getIdprov() {
		return idprov;
	}
	public void setIdprov(int idprov) {
		this.idprov = idprov;
	}
	public String getNomprov() {
		return nomprov;
	}
	public void setNomprov(String nomprov) {
		this.nomprov = nomprov;
	}
	public String getTfnprov() {
		return tfnprov;
	}
	public void setTfnprov(String tfnprov) {
		this.tfnprov = tfnprov;
	}
	public String getDirprov() {
		return dirprov;
	}
	public void setDirprov(String dirprov) {
		this.dirprov = dirprov;
	}
    
 
	public String getNombre() {
	    return nomprov;
	}
    
    
}
