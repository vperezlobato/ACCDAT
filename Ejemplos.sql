If Not Exists (SELECT * From Sys.databases Where name = 'Ejemplos' )

--DROP DATABASE Ejemplos

Create Database Ejemplos
GO
USE Ejemplos
GO

CREATE TABLE CriaturitasRaras(
	ID tinyint NOT NULL,
	Nombre nvarchar(30) NULL,
	FechaNac Date NULL, 
	NumeroPie SmallInt NULL,
	NivelIngles NChar(2) NULL,
	Historieta VarChar(30) NULL,
	NumeroChico TinyInt NULL,
	NumeroGrande BigInt NULL,
	NumeroIntermedio SmallInt NULL,
	CONSTRAINT [PK_CriaturitasEx] PRIMARY KEY(ID)
) 

GO

CREATE TABLE Regalos(
	ID tinyint NOT NULL,
	Denominacion nvarchar(50) NOT NULL,
	Ancho tinyint NULL,
	Largo tinyint NULL,
	Alto tinyint NULL,
	Tipo char(1) NULL,
	EdadMinima tinyint NOT NULL,
	Precio smallmoney NOT NULL,
	GoesTo tinyint NULL,
 CONSTRAINT PK_Regalos PRIMARY KEY (ID)
)


ALTER TABLE [dbo].[Regalos]  WITH CHECK ADD  CONSTRAINT [FK_RegalosCriaturitas] FOREIGN KEY([GoesTo])
REFERENCES [dbo].[Criaturitas] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Regalos] CHECK CONSTRAINT [FK_RegalosCriaturitas]
GO
--NumeroPie debe estar entre 25 y 60
alter table CriaturitasRaras add constraint CK_NumeroPie check (NumeroPie BETWEEN 25 AND 60)

--NumeroChico debe ser menor que 1.000
alter table CriaturitasRaras add constraint CK_NumeroChico check (NumeroChico < 1000)

--NumeroGrande debe ser mayor que NumeroChico multiplicado por 100.
alter table CriaturitasRaras add constraint CK_NumeroGrande check (NumeroGrande > (NumeroChico*100))

--NumeroMediano debe ser par y estar comprendido entre NumeroChico y NumeroGrande
alter table CriaturitasRaras add constraint CK_NumeroIntermedios check ((NumeroIntermedio % 2 = 0) AND (NumeroIntermedio BETWEEN NumeroChico AND NumeroGrande)) 

--FechaNacimiento tienen que ser anterior a la actual (CURRENT_TIMESTAMP)
alter table CriaturitasRaras add constraint CK_FechaNac check (FechaNac < CURRENT_TIMESTAMP)

--El nivel de inglés debe tener uno de los siguientes valores: A0, A1, A2, B1, B2, C1 o C2.
alter table CriaturitasRaras add constraint CK_NivelIngles check (NivelIngles IN('A0','A1','A2','B1','B2','C1','C2'))

--Historieta no puede contener las palabras "oscuro" ni "apocalipsis"
alter table CriaturitasRaras add constraint CK_Historieta check (Historieta NOT LIKE '%oscuro%' AND Historieta NOT LIKE'%apocalipsis%')

--Temperatura debe estar comprendido entre 32.5 y 44.
alter table CriaturitasRaras add Temperatura decimal(3,1) null
alter table CriaturitasRaras add constraint CK_Temperatura check (Temperatura BETWEEN 32.5 AND 44)