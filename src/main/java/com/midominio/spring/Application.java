package com.midominio.spring;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.midominio.spring.entidades.Empleado;
import com.midominio.spring.repositorios.EmpleadoRepository;
import com.midominio.spring.upload.storage.StorageService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


/**
 * Este bean se inicia al lanzar la aplicación. Nos permite inicializar el
 * almacenamiento secundario del proyecto
 * 
 * @param storageService Almacenamiento secundario del proyecto
 * @return
 */
@Bean
CommandLineRunner init(StorageService storageService) {
	return (args) -> {
		storageService.deleteAll();
		storageService.init();
	};
}


@Bean
CommandLineRunner initData(EmpleadoRepository repositorio) {
	return (args) -> {

//		Empleado empleado = new Empleado("Luis Miguel López", "luismi.lopez@openwebinars.net", "954000000");
//		Empleado empleado2 = new Empleado("José García", "jose.garcia@openwebinars.net", "954000000");
//		
//		repositorio.save(empleado);
//		repositorio.save(empleado2);
//		
//		repositorio.findAll().forEach(System.out::println);

//		repositorio.saveAll(
//				Arrays.asList(new Empleado(1, "Antonio García", "antonio.garcia@openwebinars.net", "954000000", true),
//						new Empleado(2, "María López", "maria.lopez@openwebinars.net", "954000000",false),
//						new Empleado(3, "Ángel Antúnez", "angel.antunez@openwebinars.net", "954000000", true)));
//
//	};
		
		List<String> nombres = Arrays.asList("Lucas", "Hugo", "Martín", "Daniel", "Pablo", "Alejandro", "Mateo",
				"Adrián", "Álvaro", "Manuel", "Leo", "David", "Mario", "Diego", "Javier", "Luis", "Marcos", "Juan",
				"José", "Gonzalo", "Lucía", "Sofía", "María", "Martina", "Paula", "Julia", "Daniela", "Valeria",
				"Alba", "Emma", "Carla", "Sara", "Noa", "Carmen", "Claudia", "Valentina", "Alma", "Ana", "Luisa",
				"Marta");

		List<String> apellidos = Arrays.asList("García", "González", "López", "Rodríguez", "Martínez", "Sánchez",
				"Pérez", "Gómez", "Martín", "Saez", "Velasco", "Moya", "Soler", "Parra", "Bravo", "Rojas", "Romero",
				"Sosa", "Torres", "Álvarez", "Flores", "Molina", "Ortiz", "Silva", "Torres");


		
		Collections.shuffle(nombres); //Barajamos nombres

		repositorio.saveAll(IntStream.rangeClosed(1, nombres.size()).mapToObj((i) -> {//Recorremos todo el array de nombres
			String nombre = nombres.get(i-1);
			String apellido1 = apellidos.get(ThreadLocalRandom.current().nextInt(apellidos.size())); //Apellidos aleatorio
			String apellido2 = apellidos.get(ThreadLocalRandom.current().nextInt(apellidos.size()));
			return new Empleado(String.format("%s %s %s", nombre, apellido1, apellido2), 
					String.format("%s.%s@openwebinars.net", nombre.toLowerCase(), apellido1.toLowerCase()), "954000000");
		}).collect(Collectors.toList())); //Lo pasamos con collect a una lista y que se grabará con saveAll

	};
	
}
}
