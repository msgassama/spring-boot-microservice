package com.groupeisi.service;

import com.groupeisi.dao.IIefRepository;
import com.groupeisi.dto.Ief;
import com.groupeisi.exception.EntityNotFoundException;
import com.groupeisi.exception.RequestException;
import com.groupeisi.mapping.IefMapper;
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
public class IefService {
	
	private IIefRepository iefRepository;
	private IefMapper iefMapper;
	private MessageSource messageSource;
	
	@Transactional(readOnly = true)
	public List<Ief> getIefs() {
		return StreamSupport.stream(iefRepository.findAll().spliterator(), false)
				.map(iefMapper::toIef)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Ief getIef(Integer id) {
		 return iefMapper.toIef(iefRepository.findById(id)
				 .orElseThrow(() -> 
				 new EntityNotFoundException(messageSource.getMessage("ief.notfound", new Object[]{id},
						 Locale.getDefault()))));
	}

	@Transactional(readOnly = true)
	public Ief getIefByName(String name){
		return iefMapper.toIef(Optional.ofNullable(iefRepository.findByNom(name))
				.orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("iefNom.notfound", new Object[]{name},
						Locale.getDefault()))));
	}
	
	@Transactional
	public Ief createIef(Ief ief) {
		return iefMapper.toIef(iefRepository.save(iefMapper.fromIef(ief)));
	}
	
	@Transactional
	public Ief updateIef(Integer id, Ief ief) {
		return iefRepository.findById(id)
				.map(entity -> {
					ief.setId(id);
					return iefMapper.toIef(iefRepository.save(iefMapper.fromIef(ief)));
				})
				.orElseThrow(
						() -> new EntityNotFoundException(messageSource.getMessage("ief.notfound", new Object[]{id}, Locale.getDefault()))
				);
	}
	
	@Transactional
	public void deleteIef(Integer id) {
		try {
			iefRepository.deleteById(id);
		} catch (Exception e) {
			throw new RequestException(messageSource.getMessage("ief.errordeletion", new Object[] {id},
					Locale.getDefault()), HttpStatus.CONFLICT);
		}
	}

}
