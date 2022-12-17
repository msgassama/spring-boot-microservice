package com.groupeisi.service;

import com.groupeisi.dao.IIaRepository;
import com.groupeisi.dto.Ia;
import com.groupeisi.exception.EntityNotFoundException;
import com.groupeisi.exception.RequestException;
import com.groupeisi.mapping.IaMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class IaService {
	
	private IIaRepository iaRepository;
	private IaMapper iaMapper;
	private MessageSource messageSource;
	
	@Transactional(readOnly = true)
	public List<Ia> getIas() {
		return StreamSupport.stream(iaRepository.findAll().spliterator(), false)
				.map(iaMapper::toIa)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Ia getIa(Integer id) {
		 return iaMapper.toIa(iaRepository.findById(id)
				 .orElseThrow(() -> 
				 new EntityNotFoundException(messageSource.getMessage("iaId.notfound", new Object[]{id},
						 Locale.getDefault()))));
	}

	@Transactional(readOnly = true)
	public Ia getIaByName(String name){
		return iaMapper.toIa(Optional.ofNullable(iaRepository.findByNom(name))
				.orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("iaNom.notfound", new Object[]{name},
						Locale.getDefault()))));
	}
	
	@Transactional
	public Ia createIa(Ia ia) {
		return iaMapper.toIa(iaRepository.save(iaMapper.fromIa(ia)));
	}
	
	@Transactional
	public Ia updateIa(Integer id, Ia ia) {
		return iaRepository.findById(id)
				.map(entity -> {
					ia.setId(id);
					return iaMapper.toIa(iaRepository.save(iaMapper.fromIa(ia)));
				})
				.orElseThrow(
					() -> new EntityNotFoundException(messageSource.getMessage("ia.notfound", new Object[]{id}, Locale.getDefault()))
				);
	}
	
	@Transactional
	public void deleteIa(Integer id) {
		try {
			iaRepository.deleteById(id);
		} catch (Exception e) {
			throw new RequestException(
				messageSource.getMessage("ia.errordeletion", new Object[] {id}, Locale.getDefault()),
				HttpStatus.CONFLICT
			);
		}
	}

}
