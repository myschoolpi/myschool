package app.services;

import java.util.ArrayList;

public interface BaseService {
	/**
	 * Inserção no banco de dados
	 * @param obj - objeto a ser inserido
	 * @return - mensagem de resposta
	 */
	String create(Object obj);
	
	/**
	 * Atualização no banco de dados
	 * @param obj - objeto a ser atualizado
	 * @param id - id do objeto a ser atualizado
	 * @return - objeto atualizado
	 */
	Object update(Object obj, int id);
	
	/**
	 * Consulta no banco de dados a partir de um id
	 * @param id - id do objeto a ser selecionado
	 * @return - objeto retornado da consulta
	 */
	Object getOne(int id);
	
	/**
	 * Consulta todos os objetos de um tipo no banco de dados
	 * @return - todos os objetos daquele tipo
	 */
	ArrayList<Object> getMany();
	
	/**
	 * Remoção no banco de dados
	 * @return - id do objeto deletado
	 */
	int delete();
}
