-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 09-11-2019 a las 05:54:04
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `id11404889_sensei`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Alumno`
--

CREATE TABLE `Alumno` (
  `id_alumno` int(11) NOT NULL,
  `nombre` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `ap_paterno` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `ap_materno` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` int(11) NOT NULL,
  `correo` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `contraseña` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Comentarios`
--

CREATE TABLE `Comentarios` (
  `id_comentario` int(11) NOT NULL,
  `id_alumno` int(11) NOT NULL,
  `id_tutor` int(11) NOT NULL,
  `mensaje` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `estrellas` int(5) NOT NULL,
  `id_materia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Match`
--

CREATE TABLE `Match` (
  `id_match` int(11) NOT NULL,
  `id_tutor` int(11) NOT NULL,
  `id_alumno` int(11) NOT NULL,
  `bol_tutor` tinyint(1) NOT NULL,
  `bol_alumno` tinyint(1) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `id_materia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Materia`
--

CREATE TABLE `Materia` (
  `id_materia` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Mat_Prof`
--

CREATE TABLE `Mat_Prof` (
  `id` int(11) NOT NULL,
  `id_materia` int(11) NOT NULL,
  `id_tutor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Tutor`
--

CREATE TABLE `Tutor` (
  `id_tutor` int(11) NOT NULL,
  `nombre` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `ap_paterno` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `ap_materno` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `correo` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `contraseña` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `Tutor`
--

INSERT INTO `Tutor` (`id_tutor`, `nombre`, `ap_paterno`, `ap_materno`, `telefono`, `correo`, `contraseña`) VALUES
(1, 'Juan', 'Lopez', 'Perez', '6591011111', 'juankenobi@example.com', 'juan123'),
(2, 'pepe', 'Silva', 'Vega', '6391011110', 'pepe_silva@gmail.com', 'pp123');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Alumno`
--
ALTER TABLE `Alumno`
  ADD PRIMARY KEY (`id_alumno`);

--
-- Indices de la tabla `Comentarios`
--
ALTER TABLE `Comentarios`
  ADD PRIMARY KEY (`id_comentario`);

--
-- Indices de la tabla `Match`
--
ALTER TABLE `Match`
  ADD PRIMARY KEY (`id_match`);

--
-- Indices de la tabla `Materia`
--
ALTER TABLE `Materia`
  ADD PRIMARY KEY (`id_materia`);

--
-- Indices de la tabla `Mat_Prof`
--
ALTER TABLE `Mat_Prof`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Tutor`
--
ALTER TABLE `Tutor`
  ADD PRIMARY KEY (`id_tutor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Tutor`
--
ALTER TABLE `Tutor`
  MODIFY `id_tutor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
