-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-11-2023 a las 17:05:13
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestor_turnos`
--
CREATE DATABASE IF NOT EXISTS `gestor_turnos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `gestor_turnos`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudadano`
--

DROP TABLE IF EXISTS `ciudadano`;
CREATE TABLE `ciudadano` (
  `ID` int(11) NOT NULL,
  `DIRECCION` varchar(255) NOT NULL,
  `DNI` varchar(255) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `FECHANACIMIENTO` date NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `PRIMERAPELLIDO` varchar(255) NOT NULL,
  `SEGUNDOAPELLIDO` varchar(255) NOT NULL,
  `TELEFONO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ciudadano`
--

INSERT INTO `ciudadano` (`ID`, `DIRECCION`, `DNI`, `EMAIL`, `FECHANACIMIENTO`, `NOMBRE`, `PRIMERAPELLIDO`, `SEGUNDOAPELLIDO`, `TELEFONO`) VALUES
(1, 'Calle pléyades n°11 portal 1, 2°b', '53430956r', 'aliciamm81@gmail.com', '2023-11-23', 'Alicia', 'Martinez', 'Maqueda', 615965876),
(2, 'Calle pléyades n°11 portal 1, 2°b', '45637289r', 'aliciamm81@gmail.com', '2023-11-02', 'Alicia', 'Martinez', 'Maqueda', 615965876),
(3, 'Calle pléyades n°11 portal 1, 2°b', '23423423n', 'aliciamm81@gmail.com', '2023-11-30', 'Alicia', 'Martinez', 'Maqueda', 615965876),
(4, 'Pleyades', '12348756t', 'aliole10@hotmail.com', '2023-11-08', 'Elena', 'prueba', 'prueba', 911727003),
(5, 'Calle pléyades n°11 portal 1, 2°b', '47852145r', 'aliciamm81@gmail.com', '2023-11-18', 'Alicia', 'Martinez', 'Maqueda', 615965876),
(6, 'Calle pléyades n°11 portal 1, 2°b', '12355456R', 'aliciamm81@gmail.com', '2023-11-29', 'Francisca', 'Pérez', 'González', 615965876);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tramite`
--

DROP TABLE IF EXISTS `tramite`;
CREATE TABLE `tramite` (
  `ID` int(11) NOT NULL,
  `DESCRIPCION` varchar(255) NOT NULL,
  `NOMBRE` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tramite`
--

INSERT INTO `tramite` (`ID`, `DESCRIPCION`, `NOMBRE`) VALUES
(1, 'uiyui', 'Trámites Educativos'),
(2, 'ojeras', 'Trámites de Salud'),
(3, 'agotada', 'Solicitudes de Permisos y Licencias'),
(4, 'prueba', 'Solicitudes de Permisos y Licencias'),
(5, 'prueba', 'Trámites Educativos'),
(6, 'pruebita', 'Trámites de Vivienda y Urbanismo'),
(7, 'otra mas', 'Trámites Fiscales y Financieros'),
(8, 'sdfsdaf', 'Renovación de Documentos'),
(9, 'ertert', 'Trámites de Salud'),
(10, 'Limpié la base de datos para que no te asustes :)', 'Trámites Educativos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turno`
--

DROP TABLE IF EXISTS `turno`;
CREATE TABLE `turno` (
  `ID` int(11) NOT NULL,
  `ESTADO` varchar(255) NOT NULL,
  `FECHA` date NOT NULL,
  `ID_CIUDADANO` int(11) DEFAULT NULL,
  `ID_TRAMITE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `turno`
--

INSERT INTO `turno` (`ID`, `ESTADO`, `FECHA`, `ID_CIUDADANO`, `ID_TRAMITE`) VALUES
(1, 'Atendido', '2023-11-14', 1, 1),
(2, 'Atendido', '2023-11-03', 2, 2),
(3, 'Atendido', '2023-11-21', 1, 3),
(4, 'En espera', '2023-11-28', 3, 4),
(5, 'En espera', '2023-11-16', 1, 5),
(6, 'Atendido', '2023-11-30', 1, 6),
(7, 'En espera', '2023-11-30', 4, 7),
(8, 'En espera', '2023-11-30', 1, 8),
(9, 'En espera', '2023-11-16', 5, 9),
(10, 'En espera', '2023-11-09', 6, 10);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ciudadano`
--
ALTER TABLE `ciudadano`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `DNI` (`DNI`);

--
-- Indices de la tabla `tramite`
--
ALTER TABLE `tramite`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `turno`
--
ALTER TABLE `turno`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_TURNO_ID_TRAMITE` (`ID_TRAMITE`),
  ADD KEY `FK_TURNO_ID_CIUDADANO` (`ID_CIUDADANO`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ciudadano`
--
ALTER TABLE `ciudadano`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tramite`
--
ALTER TABLE `tramite`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `turno`
--
ALTER TABLE `turno`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `turno`
--
ALTER TABLE `turno`
  ADD CONSTRAINT `FK_TURNO_ID_CIUDADANO` FOREIGN KEY (`ID_CIUDADANO`) REFERENCES `ciudadano` (`ID`),
  ADD CONSTRAINT `FK_TURNO_ID_TRAMITE` FOREIGN KEY (`ID_TRAMITE`) REFERENCES `tramite` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
