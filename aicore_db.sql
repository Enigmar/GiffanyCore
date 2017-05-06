-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 06. Mai 2017 um 17:41
-- Server Version: 5.5.54-0+deb8u1
-- PHP-Version: 5.6.30-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `aicore_db`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `aicore_keyword`
--

CREATE TABLE IF NOT EXISTS `aicore_keyword` (
`id` int(11) NOT NULL,
  `objectId` int(11) NOT NULL,
  `keyword` varchar(255) NOT NULL,
  `function` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `aicore_keyword`
--

INSERT INTO `aicore_keyword` (`id`, `objectId`, `keyword`, `function`) VALUES
(1, 1, 'testen', 'test123'),
(3, 1, 'teste', 'test123');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `aicore_object`
--

CREATE TABLE IF NOT EXISTS `aicore_object` (
`id` int(11) NOT NULL,
  `objectname` varchar(255) NOT NULL,
  `class` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `aicore_object`
--

INSERT INTO `aicore_object` (`id`, `objectname`, `class`) VALUES
(1, 'Testobject', 'TestObject');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `aicore_result`
--

CREATE TABLE IF NOT EXISTS `aicore_result` (
  `objectid` int(11) NOT NULL,
  `keywordid` int(255) NOT NULL,
  `result` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `aicore_result`
--

INSERT INTO `aicore_result` (`objectid`, `keywordid`, `result`) VALUES
(1, 1, 'Der Test wurde ausgefuehrt'),
(1, 3, 'Es wurde getestet'),
(1, 0, 'Ich weis nicht was ich damit machen soll');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `aicore_setting`
--

CREATE TABLE IF NOT EXISTS `aicore_setting` (
  `key` varchar(255) NOT NULL,
  `value` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `aicore_setting`
--

INSERT INTO `aicore_setting` (`key`, `value`) VALUES
('aicore_created', '1494057391'),
('aicore_name', 'giffany');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `aicore_keyword`
--
ALTER TABLE `aicore_keyword`
 ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `aicore_object`
--
ALTER TABLE `aicore_object`
 ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `aicore_setting`
--
ALTER TABLE `aicore_setting`
 ADD PRIMARY KEY (`key`), ADD UNIQUE KEY `key` (`key`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `aicore_keyword`
--
ALTER TABLE `aicore_keyword`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT für Tabelle `aicore_object`
--
ALTER TABLE `aicore_object`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
