-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-02-2023 a las 13:59:02
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `valar`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `codigo` int(11) NOT NULL,
  `isbn` varchar(17) DEFAULT NULL,
  `titulo` varchar(108) DEFAULT NULL,
  `autor` varchar(49) DEFAULT NULL,
  `editorial` varchar(78) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`codigo`, `isbn`, `titulo`, `autor`, `editorial`, `precio`) VALUES
(1, '978-84-9964-726-5', 'Aplicaciones gráficas con Python 3', 'Cuevas Álvarez, Alberto', 'Ra-Ma S.A. Editorial y Publicaciones', 3500),
(2, '978-84-415-3941-9', 'Aprenda a programar con Python 3', 'Shaw, Zed A. Acera García, Miguel Ángel', 'Anaya Multimedia', 2017),
(3, '978-84-415-3651-7', 'Aprenda a programar con PYTHON', 'Shaw, Zed A.', 'Anaya Multimedia', 2014),
(4, '978-84-948972-6-9', 'Aprende a programar con PYTHON', 'Luján Castillo, José Dimas', 'RC Libros', 2019),
(5, '978-84-267-3489-1', 'Aprende a programar en Python : de cero al infinito', 'Guardati Buemo, Silvia Cairó Battistutti, Osvaldo', 'Marcombo', 2022),
(6, '978-84-09-25816-1', 'Aprende machine learning en español : teoría + práctica Python', 'Bagnato, Juan Ignacio', 'Bagnato, Juan Ignacio', 2020),
(7, '978-84-09-37529-5', 'Aprende Python con Lego Spike Prime : introducción al lenguaje Python de una forma divertida', 'López Simón, Yamal', 'López Simón, Yamal', 2022),
(8, '978-84-267-2429-8', 'Aprender inteligencia artificial, combinatoria, grafos y algoritmos en Python : con 100 ejercicios prácticos', 'Pérez Castaño, Arnaldo', 'Marcombo', 2017),
(9, '978-84-17449-81-0', 'Aprendiendo a programar en Python', 'García Entrambasaguas, Paula ... [et al.]', 'Servicio de Publicaciones y Divulgación Científica de la Universidad de Málaga', 2019),
(10, '978-84-17449-82-7', 'Aprendiendo a programar en Python', 'García Entrambasaguas, Paula ... [et al.]', 'Servicio de Publicaciones y Divulgación Científica de la Universidad de Málaga', 2019),
(11, '978-84-9881-405-7', 'Aprendizaje del lenguaje Python en FP', 'Arroyo Juan, Manuel', 'Procompal Publicaciones', 2010),
(12, '978-07-65326-35-5', 'El Camino De Los Reyes', 'Brandon Sanderson', 'DragonSteel', 2009),
(13, '978-84-18037-81-8', 'Trenza del Mar Esmeralda', 'Brandon Sanderson', 'nova', 2690),
(14, '978-84-99899-61-9', 'El Temor de un Hombre Sabio', 'Patrick Rothfuss', 'DEBOLSILLO', 1095),
(15, '978-84-99893-67-9', 'El camino de las sombras', 'Brent Weeks', 'DEBOLSILLO', 995),
(16, '978-84-66657-54-9', 'Palabras Radiantes', 'Brandon Sanderson', 'NOVA', 1420),
(17, '978-84-17347-00-0', 'Juramentada', 'Brandon Sanderson', 'NOVA', 3315),
(18, '978-84-17347-93-2', 'El Ritmo de la Guerra', 'Brandon Sanderson', 'NOVA', 3315),
(19, '123-45-67894-23-6', 'El Quijote', 'Miguel de Cervantes', 'Planeta', 1000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedores`
--

CREATE TABLE `vendedores` (
  `id_vendedor` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `username` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vendedores`
--

INSERT INTO `vendedores` (`id_vendedor`, `nombre`, `username`) VALUES
(1, 'Sergio Fernández', 'Admin'),
(2, 'Pablo Antonio', 'pobloca');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id_venta` int(11) NOT NULL,
  `isbn` varchar(17) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_vendedor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`codigo`),
  ADD UNIQUE KEY `isbn` (`isbn`);

--
-- Indices de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  ADD PRIMARY KEY (`id_vendedor`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `isbn` (`isbn`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_vendedor` (`id_vendedor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  MODIFY `id_vendedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `libros` (`isbn`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ventas_ibfk_3` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedores` (`id_vendedor`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
