-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Tempo de geração: 07/05/2024 às 16:24
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `pousada`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbl_cliente`
--

CREATE TABLE `tbl_cliente` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cpf` char(11) NOT NULL,
  `endereco` varchar(100) NOT NULL,
  `ddd` int(2) NOT NULL,
  `telefone` int(9) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `estado` char(2) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbl_login`
--

CREATE TABLE `tbl_login` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tbl_login`
--

INSERT INTO `tbl_login` (`id`, `email`, `senha`) VALUES
(1, 'test', 'test'),
(4, 'email@gmail.com', '123');

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbl_quartos`
--

CREATE TABLE `tbl_quartos` (
  `numero` int(2) NOT NULL,
  `situacao` varchar(12) DEFAULT 'DISPONÍVEL',
  `tipo` varchar(20) NOT NULL,
  `camas` tinyint(2) NOT NULL,
  `dataReserva` date DEFAULT NULL,
  `dataSaida` date DEFAULT NULL,
  `preco` double NOT NULL,
  `descricao` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tbl_quartos`
--

INSERT INTO `tbl_quartos` (`numero`, `situacao`, `tipo`, `camas`, `dataReserva`, `dataSaida`, `preco`, `descricao`) VALUES
(1, 'INDISPONÍVEL', 'Solteiro', 1, '2001-12-12', '2001-12-15', 1.9, 'Quarto para pobre premium que necessita descançar. possui um banheiro de 1 metro quadrado, e um lindo brinde para aqueles que irão alugar, uma bola de tenis roxa, incrivel!,né?!?!. '),
(2, 'DISPONÍVEL', 'Casal', 2, NULL, NULL, 50, 'Quarto com uma cama de casal e outra de solteiro.'),
(3, 'DISPONÍVEL', 'Solteiro', 1, NULL, NULL, 50, 'Uma cama de solteiro com TV e toca fitas.'),
(4, 'DISPONÍVEL', 'Casal', 2, NULL, NULL, 50, 'Quarto com duas camas de casal.'),
(5, 'DISPONÍVEL', 'Solteiro', 1, NULL, NULL, 50, 'Quarto com uma cama de solteiro e TV.'),
(6, 'DISPONÍVEL', 'Duplo', 2, NULL, NULL, 70, 'Quarto com duas camas de solteiro.'),
(7, 'DISPONÍVEL', 'Família', 3, NULL, NULL, 80, 'Quarto espaçoso com três camas de solteiro.'),
(8, 'DISPONÍVEL', 'Suite', 1, NULL, NULL, 100, 'Suite com cama de casal, TV e banheira de hidromassagem.'),
(9, 'DISPONÍVEL', 'Casal', 2, NULL, NULL, 50, 'Quarto com duas camas de casal e varanda.'),
(10, 'DISPONÍVEL', 'Solteiro', 1, NULL, NULL, 50, 'Quarto com uma cama de solteiro e vista para o mar.'),
(11, 'DISPONÍVEL', 'Duplo', 2, NULL, NULL, 70, 'Quarto com duas camas de solteiro e ar condicionado.'),
(12, 'DISPONÍVEL', 'Família', 3, NULL, NULL, 80, 'Quarto espaçoso com três camas de solteiro e área de estar.'),
(13, 'DISPONÍVEL', 'Suite', 1, NULL, NULL, 100, 'Suite com cama de casal, TV, banheira de hidromassagem e varanda.'),
(14, 'DISPONÍVEL', 'Casal', 2, NULL, NULL, 60, 'Quarto com cama de casal, TV e frigobar.'),
(15, 'DISPONÍVEL', 'Solteiro', 1, NULL, NULL, 60, 'Quarto com uma cama de solteiro, TV e mesa de trabalho.'),
(16, 'DISPONÍVEL', 'Duplo', 2, NULL, NULL, 80, 'Quarto com duas camas de solteiro, TV e varanda.'),
(17, 'DISPONÍVEL', 'Família', 3, NULL, NULL, 90, 'Quarto amplo com três camas de solteiro, TV e área de lazer.'),
(18, 'DISPONÍVEL', 'Suite', 1, NULL, NULL, 120, 'Suite com cama de casal, TV, banheira de hidromassagem e vista para o jardim.'),
(19, 'DISPONÍVEL', 'Casal', 2, NULL, NULL, 70, 'Quarto com cama de casal, TV e banheiro privativo.'),
(20, 'DISPONÍVEL', 'Solteiro', 1, NULL, NULL, 70, 'Quarto com uma cama de solteiro, TV e varanda.'),
(21, 'DISPONÍVEL', 'Duplo', 2, NULL, NULL, 90, 'Quarto com duas camas de solteiro, TV e frigobar.'),
(22, 'DISPONÍVEL', 'Família', 3, NULL, NULL, 100, 'Quarto espaçoso com três camas de solteiro, TV e área de estar.'),
(23, 'DISPONÍVEL', 'Suite', 1, NULL, NULL, 130, 'Suite com cama de casal, TV, banheira de hidromassagem e vista para o mar.'),
(24, 'DISPONÍVEL', 'Casal', 2, NULL, NULL, 80, 'Quarto com cama de casal, TV, frigobar e vista para a piscina.'),
(25, 'DISPONÍVEL', 'Solteiro', 1, NULL, NULL, 80, 'Quarto com uma cama de solteiro, TV, mesa de trabalho e ar condicionado.'),
(26, 'DISPONÍVEL', 'Duplo', 2, NULL, NULL, 100, 'Quarto com duas camas de solteiro, TV, varanda e área de lazer.'),
(27, 'DISPONÍVEL', 'Família', 3, NULL, NULL, 110, 'Quarto amplo com três camas de solteiro, TV, área de estar e vista para o jardim.'),
(28, 'DISPONÍVEL', 'Suite', 1, NULL, NULL, 140, 'Suite com cama de casal, TV, banheira de hidromassagem e varanda.'),
(29, 'DISPONÍVEL', 'Casal', 2, NULL, NULL, 90, 'Quarto com cama de casal, TV, frigobar e vista para o mar.'),
(30, 'DISPONÍVEL', 'Solteiro', 1, NULL, NULL, 90, 'Quarto com uma cama de solteiro, TV, mesa de trabalho e vista para a piscina.');

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbl_veri`
--

CREATE TABLE `tbl_veri` (
  `check-in` date NOT NULL,
  `checkout` date NOT NULL,
  `quartosL` tinyint(4) NOT NULL DEFAULT 30
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `tbl_cliente`
--
ALTER TABLE `tbl_cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cpf` (`cpf`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Índices de tabela `tbl_login`
--
ALTER TABLE `tbl_login`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `tbl_quartos`
--
ALTER TABLE `tbl_quartos`
  ADD PRIMARY KEY (`numero`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tbl_cliente`
--
ALTER TABLE `tbl_cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `tbl_login`
--
ALTER TABLE `tbl_login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
