package com.capgemini.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.capgemini.curso.model.Curso;
import com.capgemini.curso.repository.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService {

	//Dependency injection
	//CursoServiceImpl (CursoRepository cursoRepository){
	//	this.cursoRepository = cursoRepository;}
	@Autowired
	private CursoRepository cursoRepository;
	
	@Override
	public List<Curso> getAllCursos() {
		return this.cursoRepository.findAll();	//Viene de JpaRepository
	}

	@Override
	public Curso getCursoById(long id) {
		//Lo siguente equivale a 
		//Curso curso = this.cursoRepository.getById(id).orElse(null);
		Optional<Curso> optionalCurso = cursoRepository.findById(id);
		Curso curso = null;
		if (optionalCurso.isPresent()) {
			curso = optionalCurso.get();
		} else throw new RuntimeException("El curso " + id + " no se encuentra");
		return curso;
	}

	@Override
	public void saveCurso(Curso curso) {
		this.cursoRepository.save(curso);	//Viene de CrudRepository
	}

	@Override
	public void deleteCursoById(long id) {
		this.cursoRepository.deleteById(id); //Viene de CrudRepository
	}

	@Override
	public Page<Curso> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
		// if reducido -- (pregunta logica ? true : false) que se usa para guardar el resultado en una variable
		Sort sort = 
				sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())?
						Sort.by(sortField).ascending() :
							Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
		return this.cursoRepository.findAll(pageable);
	}

}







