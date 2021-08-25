CREATE TABLE `formateurs` (
  `id` int(11) NOT NULL,
  `nom` varchar(12) NOT NULL,
  `prenom` varchar(12) NOT NULL,
  `adresse_email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `formations` (
  `code` varchar(20) NOT NULL,
  `libelle` varchar(200) NOT NULL,
  `descriptif` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `seances` (
  `code_formation` varchar(20) NOT NULL,
  `id_formateur` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;