use PersonasDB
go

create table PD_Departamentos
(
	IdDepartamento int identity(1,1) not null,
	NombreDepartamento varchar (20) null,

	constraint PK_PD_Departamentos primary key (IdDepartamento)
)
go

create table PD_Personas
(
	IdPersona int identity(1,1) not null,
	NombrePersona varchar (20) null,
	ApellidosPersona varchar (30) null,
	IDDepartamento int null,
	FechaNacimientoPersona date null,
	TelefonoPersona varchar (15) null,
	FotoPersona varbinary(max) null,

	constraint PK_PD_Personas primary key (IdPersona),
	constraint FK_Persona_Departamento foreign key (IDDepartamento) 
	references PD_Departamentos(IdDepartamento) on delete no action on update cascade
)

--drop database PersonaDepartamento