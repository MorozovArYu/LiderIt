DROP TABLE IF EXISTS public.players;
CREATE TABLE public.players
(
    id serial NOT NULL,
    team_id integer,
    name character varying(120) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(120) COLLATE pg_catalog."default" NOT NULL,
    patronymic character varying(120) COLLATE pg_catalog."default",
    birth_date date NOT NULL,
    role character varying(120) COLLATE pg_catalog."default",
    CONSTRAINT players_pkey PRIMARY KEY (id),
    CONSTRAINT teams_fkey FOREIGN KEY (team_id)
        REFERENCES public.teams (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.players
    OWNER to test;