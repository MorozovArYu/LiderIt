DROP TABLE IF EXISTS public.teams;
CREATE TABLE public.teams
(
    id serial NOT NULL ,
    name character varying(120) COLLATE pg_catalog."default" NOT NULL,
    sport_kind character varying(120) COLLATE pg_catalog."default",
    creation_date date NOT NULL,
    CONSTRAINT teams_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.teams
    OWNER to test;