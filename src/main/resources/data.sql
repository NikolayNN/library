-- --------------------------
-- Records of genre
-- --------------------------
INSERT INTO public.genre(id, name)
	VALUES (1, 'fantasy');
INSERT INTO public.genre(id, name)
	VALUES (2, 'detective');
INSERT INTO public.genre(id, name)
	VALUES (3, 'thriller');
INSERT INTO public.genre(id, name)
	VALUES (4, 'history');
INSERT INTO public.genre(id, name)
	VALUES (5, 'anthology');
INSERT INTO public.genre(id, name)
	VALUES (6, 'horror');
INSERT INTO public.genre(id, name)
	VALUES (7, 'autobiography');
INSERT INTO public.genre(id, name)
	VALUES (8, 'dictionary');
INSERT INTO public.genre(id, name)
	VALUES (9, 'encyclopedia');
INSERT INTO public.genre(id, name)
	VALUES (10, 'romance');
INSERT INTO public.genre(id, name)
	VALUES (11, 'satire');

-- --------------------------
-- Records of author
-- --------------------------
INSERT INTO public.author(id, name)
	VALUES (1, 'Joanne Rowling');
INSERT INTO public.author(id, name)
	VALUES (2, 'George Orwell');
INSERT INTO public.author(id, name)
	VALUES (3, 'Harper Lee');

-- --------------------------
-- Records of book
-- --------------------------
INSERT INTO public.book(id, name, genre_id)
	VALUES (1, 'Harry Potter', 1);
INSERT INTO public.book(id, name, genre_id)
	VALUES (2, 'Animal Farm', 11);
INSERT INTO public.book(id, name, genre_id)
	VALUES (3, 'To kill a Mockingbird', 10);
INSERT INTO public.book(id, name, genre_id)
	VALUES (4, 'Super book', 6);

-- --------------------------
-- Records of book_author
-- --------------------------
INSERT INTO public.book_author(book_id, author_id)
	VALUES (1, 1);
INSERT INTO public.book_author(book_id, author_id)
	VALUES (2, 2);
INSERT INTO public.book_author(book_id, author_id)
	VALUES (3, 3);
INSERT INTO public.book_author(book_id, author_id)
	VALUES (4, 1);
INSERT INTO public.book_author(book_id, author_id)
	VALUES (4, 2);
INSERT INTO public.book_author(book_id, author_id)
	VALUES (4, 3);