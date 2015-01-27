package it.obiectivo.ecoss.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.obiectivo.ecoss.domain.CheckDate;

@Service("checkDateService")
@Transactional
public abstract class CheckDateService {
	public abstract Boolean edit(CheckDate obj);
}