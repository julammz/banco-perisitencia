package co.edu.usbcali.demo.logica;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import co.edu.usbcali.demo.dao.ITiposUsuariosDAO;
import co.edu.usbcali.demo.dao.IUsuariosDAO;
import co.edu.usbcali.demo.modelo.Usuarios;

@Service
@Scope("singleton")
public class UsuariosLogica implements IUsuariosLogica {
	
	@Autowired
	private IUsuariosDAO usuariosDAO;
	
	@Autowired
	private ITiposUsuariosDAO tipoUsuariosDAO;
	
	@Autowired
	private Validator validator;
	
	public void validarUsuarios(Usuarios usuarios) throws Exception {
	    try {
	        Set<ConstraintViolation<Usuarios>> constraintViolations = validator.validate(usuarios);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<Usuarios> constraintViolation : constraintViolations) {
	                strMessage.append(constraintViolation.getPropertyPath()
	                                                     .toString());
	                strMessage.append(" - ");
	                strMessage.append(constraintViolation.getMessage());
	                strMessage.append(". \n");
	            }

	            throw new Exception(strMessage.toString());
	        }
	    } catch (Exception e) {
	        throw e;
	    }
	}
	

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void crear(Usuarios entity) throws Exception {

		if(entity==null){
			throw new Exception("El usuario es nulo");
		}
		
		validarUsuarios(entity);
		
		Usuarios usuarios = usuariosDAO.consultarPorId(entity.getUsuCedula());
		
		if(usuarios!=null){
			throw new Exception("El usuario ya existe");
		}
		
		usuariosDAO.crear(entity);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificar(Usuarios entity) throws Exception {
		if(entity==null){
			throw new Exception("El cliente es nulo");
		}
		
		validarUsuarios(entity);
		
		usuariosDAO.modificar(entity);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void borrar(Usuarios entity) throws Exception {
		if(entity==null){
			throw new Exception("El cliente es nulo");
		}
		
		Usuarios usuarios = usuariosDAO.consultarPorId(entity.getUsuCedula());
		
		if(usuarios==null){
			throw new Exception("El cliente que desea borrar no existe");
		}
		
		usuariosDAO.borrar(usuarios);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuarios consultarPorId(Long usuCedula) throws Exception {		
		return usuariosDAO.consultarPorId(usuCedula);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuarios> consultar() {
		return usuariosDAO.consultar();
	}

}
