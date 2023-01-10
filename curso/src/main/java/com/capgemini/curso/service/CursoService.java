package com.capgemini.curso.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.capgemini.curso.model.Curso;

/**
 * Dialoga hacia el front-end
 * @author Marta Rodríguez
 *
 */
public interface CursoService {
	
	List<Curso> getAllCursos();
	
	Curso getCursoById(long id);
	
	void saveCurso(Curso curso);
	
	void deleteCursoById(long id);
	
	/**
	 * Paginación de elementos
	 * @param pageNum Número de página
	 * @param pageSize Elementos por página
	 * @param sortField Campo por el que ordenamos (nombre, apellido, mail....)
	 * @param sortDirection Dirección de ordenamiento (ASC/DESC)
	 * @return
	 */
	Page<Curso> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection);
}
