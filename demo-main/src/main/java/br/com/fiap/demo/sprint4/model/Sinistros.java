package br.com.fiap.demo.sprint4.model;

public record Sinistros (
		Long id,
		String data_sinistro,
		String descricao,
		Long valor_prejuizo,
		Long id_cliente,
		Long id_bike
){}
