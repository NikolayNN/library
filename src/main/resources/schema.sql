-- --------------------------
-- drop script
-- --------------------------

ALTER TABLE "book" DROP CONSTRAINT IF EXISTS "book_fk0";

ALTER TABLE "book_author" DROP CONSTRAINT IF EXISTS "book_author_fk0";

ALTER TABLE "book_author" DROP CONSTRAINT IF EXISTS "book_author_fk1";

DROP TABLE IF EXISTS "author";

DROP TABLE IF EXISTS "genre";

DROP TABLE IF EXISTS "book";

DROP TABLE IF EXISTS "book_author";

-- --------------------------
-- create script
-- --------------------------
CREATE TABLE "book" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	"genre_id" integer NOT NULL,
	CONSTRAINT book_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "genre" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL UNIQUE,
	CONSTRAINT genre_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "author" (
	"id" serial NOT NULL,
	"name" varchar NOT NULL,
	CONSTRAINT author_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "book_author" (
	"book_id" integer NOT NULL,
	"author_id" integer NOT NULL,
	CONSTRAINT book_author_pk PRIMARY KEY ("book_id","author_id")
) WITH (
  OIDS=FALSE
);

ALTER TABLE "book" ADD CONSTRAINT "book_fk0" FOREIGN KEY ("genre_id") REFERENCES "genre"("id");

ALTER TABLE "book_author" ADD CONSTRAINT "book_author_fk0" FOREIGN KEY ("book_id") REFERENCES "book"("id");
ALTER TABLE "book_author" ADD CONSTRAINT "book_author_fk1" FOREIGN KEY ("author_id") REFERENCES "author"("id");
