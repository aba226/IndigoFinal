-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 22 Février 2018 à 21:52
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `ogidni`
--

-- --------------------------------------------------------

--
-- Structure de la table `articles`
--

CREATE TABLE `articles` (
  `code` varchar(6) NOT NULL,
  `code_categorie` char(3) NOT NULL,
  `designation` varchar(100) NOT NULL,
  `quantite` int(11) NOT NULL,
  `prix_unitaire` double NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `articles`
--

INSERT INTO `articles` (`code`, `code_categorie`, `designation`, `quantite`, `prix_unitaire`, `date`) VALUES
('FAMI21', 'ECR', 'Famir21', 20, 150, '2010-11-08'),
('i5965', 'ECR', 'DESIGNATION', 1, 500, '2018-02-12'),
('XENO25', 'POR', 'Xenon25', 15, 850, '2011-12-20'),
('ZENI77', 'POR', 'HIKEA', 2, 3000, '2011-11-11');

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE `categories` (
  `code` char(3) NOT NULL,
  `designation` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `categories`
--

INSERT INTO `categories` (`code`, `designation`) VALUES
('ECR', 'écran'),
('POR', 'portable'),
('TAB', 'tablette');

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `code` varchar(6) NOT NULL,
  `nom` varchar(15) NOT NULL,
  `prenom` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `clients`
--

INSERT INTO `clients` (`code`, `nom`, `prenom`) VALUES
('', '', ''),
('4523', 'Koui', 'Flinder'),
('5256', 'Koui', 'Mamal'),
('5896', 'KaDjo', ' Pierre'),
('5969', 'Zeze', 'Wiliams'),
('756852', 'AHOU', 'BERTRAND'),
('DRJE02', 'DROUAN', 'jean'),
('GIPA01', 'GIELAU', 'pascal'),
('jkip', 'klkl', 'n,k;kk'),
('PLSY01', 'PLAFONI', 'sylvie');

-- --------------------------------------------------------

--
-- Structure de la table `commandes`
--

CREATE TABLE `commandes` (
  `code` varchar(15) NOT NULL,
  `code_client` varchar(6) NOT NULL,
  `total_ttc` double NOT NULL,
  `code_mode_reglement` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `commandes`
--

INSERT INTO `commandes` (`code`, `code_client`, `total_ttc`, `code_mode_reglement`, `date`) VALUES
('108bien', '4523', 6000, 1, '2018-02-17'),
('1910bien', '4523', 2550, 1, '2018-02-17'),
('191bien', '4523', 0, 1, '2018-02-17'),
('1939bien', '4523', 0, 1, '2018-02-19'),
('2594bien', '4523', 0, 1, '2018-02-17'),
('309bien', '4523', 0, 1, '2018-02-17'),
('5057bien', '4523', 3150, 1, '2018-02-16'),
('5307bien', '5969', 0, 1, '2018-02-17'),
('5543bien', '4523', 0, 1, '2018-02-17'),
('5586bien', '4523', 0, 1, '2018-02-17'),
('6288bien', '4523', 0, 1, '2018-02-16'),
('696bien', '4523', 2550, 1, '2018-02-17'),
('7052bien', '4523', 0, 1, '2018-02-17'),
('8744bien', '4523', 0, 1, '2018-02-19'),
('9547bien', '4523', 750, 1, '2018-02-19');

-- --------------------------------------------------------

--
-- Structure de la table `lignes_commandes`
--

CREATE TABLE `lignes_commandes` (
  `code_commande` varchar(15) NOT NULL,
  `code_article` varchar(6) NOT NULL,
  `quantite` int(11) NOT NULL,
  `prix_unitaire` double NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `lignes_commandes`
--

INSERT INTO `lignes_commandes` (`code_commande`, `code_article`, `quantite`, `prix_unitaire`, `total`) VALUES
('COM1', 'XENO25', 1, 850, 1025),
('COM1', 'ZENI77', 1, 500, 630),
('COM2', 'FAMI21', 2, 150, 361);

-- --------------------------------------------------------

--
-- Structure de la table `mode_reglements`
--

CREATE TABLE `mode_reglements` (
  `code` int(1) NOT NULL,
  `type` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `mode_reglements`
--

INSERT INTO `mode_reglements` (`code`, `type`) VALUES
(1, 'espèce'),
(2, 'chèque'),
(3, 'carte');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `code` int(15) NOT NULL,
  `nomUtilisateur` varchar(25) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`code`, `nomUtilisateur`, `nom`, `prenom`) VALUES
(1, 'jordanien', 'Badie', 'Landry'),
(2, 'SKELLY', 'ABLA', 'POKOU'),
(3, 'Chigata', 'koui', 'Flinder'),
(4, 'Scoty', 'Fofana', 'Allassane'),
(5, 'jacques', 'Kablan', 'jean'),
(6, 'Dumas', 'Ngoran', 'Magloire'),
(7, 'Dumas', 'Ngoran', 'Magloire'),
(11, '', '', ''),
(9, 'Jojoritto', 'KOUA', 'RUDY'),
(10, 'Bison', 'Koa', 'Lapin'),
(12, '', '', ''),
(13, '', '', '');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`code`),
  ADD KEY `code_categorie` (`code_categorie`),
  ADD KEY `designation` (`designation`);

--
-- Index pour la table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`code`),
  ADD KEY `designation` (`designation`);

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`code`),
  ADD KEY `nom` (`nom`),
  ADD KEY `prenom` (`prenom`);

--
-- Index pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD PRIMARY KEY (`code`),
  ADD KEY `code_client` (`code_client`),
  ADD KEY `code_mode_reglement` (`code_mode_reglement`);

--
-- Index pour la table `lignes_commandes`
--
ALTER TABLE `lignes_commandes`
  ADD PRIMARY KEY (`code_commande`,`code_article`),
  ADD KEY `fk_code_article` (`code_article`);

--
-- Index pour la table `mode_reglements`
--
ALTER TABLE `mode_reglements`
  ADD PRIMARY KEY (`code`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`code`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `code` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `articles`
--
ALTER TABLE `articles`
  ADD CONSTRAINT `fk_code_categorie` FOREIGN KEY (`code_categorie`) REFERENCES `categories` (`code`);

--
-- Contraintes pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `fk_code_client` FOREIGN KEY (`code_client`) REFERENCES `clients` (`code`),
  ADD CONSTRAINT `fk_code_mode_reglement` FOREIGN KEY (`code_mode_reglement`) REFERENCES `mode_reglements` (`code`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
