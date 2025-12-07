describe('Library Book Management', () => {
  beforeEach(() => {
    // Reset items before each test
    // Make sure that the initial page landed is the login page
    // So that the program will be in initial state before each test
    cy.request('POST', 'http://localhost:3000/api/reset');
    cy.visit('http://localhost:3000/login.html');
  });

  afterEach(() => {
      // Reset items before each test
      // Make sure that the initial page landed is the login page
      // So that the program will be in initial state after each test
      cy.request('POST', 'http://localhost:3000/api/reset');
      cy.visit('http://localhost:3000/login.html');
    });

  // Test Scenario 1
  it('should test the basic borrow-return cycle with two users and one book', () => {

    // Login as alice
    cy.login('alice','pass123');

    // Go to the borrow book screen
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the Status of the book to borrow
    // So that we know that the book is available to borrow
    cy.get('#itemList').should('contain', '5 | The Hobbit | J.R.R. Tolkien | Available');

    // Borrow the Book
    cy.get('#itemInput').type('5');
    cy.get('#submitButton').click();

    // Calculate the Expected Date of Return, which is 2 weeks later
    const today = new Date();
    const newDate = new Date();
    newDate.setDate(today.getDate() + 14);
    const returnDate = newDate.toISOString().split('T')[0];

    // Check if the expected return date is as expected
    // So that the return date is valid
    cy.get('#itemList').should('contain', 'Expected Return Date: ' + returnDate);

    // Confirm the borrowing process
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Verify that the success prompt is present in the log
    // So that we can verify that the book has been borrowed successfully from the user
    cy.get('#itemList').should('contain', 'Book successfully borrowed.');

    // Go to the borrow book screen
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the status of the book
    // So that we can see if has been borrowed by our user, with the right date
    cy.get('#itemList').should('contain', '5 | The Hobbit | J.R.R. Tolkien | *Checked Out, Due: ' + returnDate);

    // Logout user from session
    // Make sure that we are on login screen
    cy.get('#logoutButton').click();
    cy.url().should('include', '/login.html');

    // Now Login as bob
    cy.login('bob','pass456');

    // Go to the borrow book screen
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check that the book is checked out from a different user
    // So that the book is borrowed from a different session
    cy.get('#itemList').should('contain', '5 | The Hobbit | J.R.R. Tolkien | Checked Out, Due: ' + returnDate);

    // Try to Borrow that same book
    cy.get('#itemInput').type('5');
    cy.get('#submitButton').click();

    // Verify the error prompt
    // So that we can verify that only 1 user can borrow a book
    cy.get('#itemList').should('contain', 'Book borrowing unsuccessful. Someone is borrowing this book.');

    // Logout user from session
    // Make sure that we are on login screen
    cy.get('#logoutButton').click();
    cy.url().should('include', '/login.html');

    // Now login as alice
    cy.login('alice','pass123');

    // Go to Return the book
    cy.get('#itemInput').type('2');
    cy.get('#submitButton').click();

    // Verify the prompt with the book details
    // So we can check if the book details are stored in there
    cy.get('#itemList').should('contain', '5 | The Hobbit | J.R.R. Tolkien | Checked Out, Due: ' + returnDate);

    // Now return this book with confirmation
    cy.get('#itemInput').type('5');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Verify the prompt from the return process
    // So we can verify that the book was successfully returned
    cy.get('#itemList').should('contain', 'Book returned.');

    cy.get('#itemInput').type('2');
    cy.get('#submitButton').click();

    // Verify the prompt from the return selection
    // So we can verify that the user don't have borrowed books anymore
    cy.get('#itemList').should('contain', 'No books to return.');

    // Logout user from session
    // Make sure that we are on login screen
    cy.get('#logoutButton').click();
    cy.url().should('include', '/login.html');

    // Login as bob
    cy.login('bob','pass456');

    // Try to borrow the book
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Verify the prompt with the book details
    // So we can check if the book if available
    cy.get('#itemList').should('contain', '5 | The Hobbit | J.R.R. Tolkien | Available');

    // Borrow that book
    cy.get('#itemInput').type('5');
    cy.get('#submitButton').click();

    // Check if the expected return date is valid
    // So that the return date is expected
    cy.get('#itemList').should('contain', 'Expected Return Date: ' + returnDate);

    // Confirm the borrowing process
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the prompt if the book has been borrowed
    // So that we can verify that the book has been borrowed from the user
    cy.get('#itemList').should('contain', 'Book successfully borrowed.');

    // Go to the book borrowing selection
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the prompt of the book
    // So we can verify the status of the book from the current user
    cy.get('#itemList').should('contain', '5 | The Hobbit | J.R.R. Tolkien | *Checked Out, Due: ' + returnDate);

    // Logout user from session
    // So that the session is now cleared
    cy.get('#logoutButton').click();
    cy.url().should('include', '/login.html');

  });

  // Test Scenario 2
  it('should test the hold queue system with three users competing for the same book', () => {

        // Login as alice
        cy.login('alice','pass123');

        // Try to borrow a book
        cy.get('#itemInput').type('1');
        cy.get('#submitButton').click();

        // Check the Status of the book
        // So that we know that the book is available
        cy.get('#itemList').should('contain', '3 | 1984 | George Orwell | Available');

        // Borrow that book
        cy.get('#itemInput').type('3');
        cy.get('#submitButton').click();

        // Calculate the Expected Date of Return, which is 2 weeks later
        const today = new Date();
        const newDate = new Date();
        newDate.setDate(today.getDate() + 14);
        const returnDate = newDate.toISOString().split('T')[0];

        // Check if the expected return date is as expected
        // So that the return date is valid
        cy.get('#itemList').should('contain', 'Expected Return Date: ' + returnDate);

        // Confirm the borrowing process
        cy.get('#itemInput').type('1');
        cy.get('#submitButton').click();

        // Check the success prompt in output
        // To Verify that the book has been borrowed from the user
        cy.get('#itemList').should('contain', 'Book successfully borrowed.');

        // Logout user from session
        // So that we can login as another user
        cy.get('#logoutButton').click();
        cy.url().should('include', '/login.html');

        // Login as bob
        cy.login('bob','pass456');

        // Try to Borrow the same book
        cy.get('#itemInput').type('1');
        cy.get('#submitButton').click();

        // Check the status of the book
        // So that we can know that it has been checked out from another user
        cy.get('#itemList').should('contain', '3 | 1984 | George Orwell | Checked Out, Due: ' + returnDate);

        // Try to borrow anyways
        cy.get('#itemInput').type('3');
        cy.get('#submitButton').click();

        // Check the prompt
        // So that we can see that someone is borrowing this book
        cy.get('#itemList').should('contain', 'Book borrowing unsuccessful. Someone is borrowing this book.');

        // Hold this book, since cannot be borrowed
        cy.get('#itemInput').type('1');
        cy.get('#submitButton').click();

        // Logout user from session
        // So that we can login as another user
        cy.get('#logoutButton').click();
        cy.url().should('include', '/login.html');

        // Login as charlie
        cy.login('charlie','pass789');

        // Try to Borrow the same book
        cy.get('#itemInput').type('1');
        cy.get('#submitButton').click();

        // Check the status of the book
        // So that we can know that it has been checked out from another user
        cy.get('#itemList').should('contain', '3 | 1984 | George Orwell | Checked Out, Due: ' + returnDate);

        // Try to borrow anyways
        cy.get('#itemInput').type('3');
        cy.get('#submitButton').click();

        // Check the prompt
        // So that we can see that someone is borrowing this book
        cy.get('#itemList').should('contain', 'Book borrowing unsuccessful. Someone is borrowing this book.');

        // Hold this book, since cannot be borrowed
        cy.get('#itemInput').type('1');
        cy.get('#submitButton').click();

        // Logout user from session
        // So that we can login as another user
        cy.get('#logoutButton').click();
        cy.url().should('include', '/login.html');

        // Login as alice
        cy.login('alice','pass123');

        // Return the book
        cy.get('#itemInput').type('2');
        cy.get('#submitButton').click();

        // Check the returning books prompt
        // Check if the book is listed with the right details
        cy.get('#itemList').should('contain', '3 | 1984 | George Orwell | Checked Out, Due: ' + returnDate);

        // Return the Book
        cy.get('#itemInput').type('3');
        cy.get('#submitButton').click();
        cy.get('#itemInput').type('1');
        cy.get('#submitButton').click();

        // Check the returning books prompt
        // So that we know the book has been successfully returned
        cy.get('#itemList').should('contain', 'Book returned.');

        // Try to list the returning books
        cy.get('#itemInput').type('2');
        cy.get('#submitButton').click();

        // Check the returning books display prompt
        // So that we can Verify that the user has no books
        cy.get('#itemList').should('contain', 'No books to return.');

        // Logout user from session
        // So that we can login as another user
        cy.get('#logoutButton').click();
        cy.url().should('include', '/login.html');

        // Login as Charlie
        cy.login('charlie','pass789');

        // Check the prompt at start
        // So that we make sure charlie has not received a notification
        cy.get('#itemList').should('not.contain', 'NOTICE: The book 1984 is now available.');

        // Try to Borrow the same book
        cy.get('#itemInput').type('1');
        cy.get('#submitButton').click();

        // Check the borrowing books prompt
        // So we can check if the book is listed with the right details
        cy.get('#itemList').should('contain', '3 | 1984 | George Orwell | Available');

        // Try to borrow that book
        cy.get('#itemInput').type('3');
        cy.get('#submitButton').click();
        cy.get('#itemInput').type('1');
        cy.get('#submitButton').click();

        // Check the error prompt
        // The user will be prevented to borrow the book due to not in the first FIFO Queue Hold
        cy.get('#itemList').should('contain', 'Failed to borrow book.');

        // Logout user from session
        // So that we can login as another user
        cy.get('#logoutButton').click();
        cy.url().should('include', '/login.html');

        // Now login as bob
        cy.login('bob','pass456');

        // Check the notification at start in the prompt
        // So that we can make sure bob received a notification, because he is the first in FIFO queue
        cy.get('#itemList').should('contain', 'NOTICE: The book 1984 is now available.');

        // Try to Borrow the same book
        cy.get('#itemInput').type('1');
        cy.get('#submitButton').click();

        // Check the borrowing books prompt
        // So we can check if the book is listed with the right details
        cy.get('#itemList').should('contain', '3 | 1984 | George Orwell | Available');

        // Try to borrow that book
        cy.get('#itemInput').type('3');
        cy.get('#submitButton').click();
        cy.get('#itemInput').type('1');
        cy.get('#submitButton').click();

        // Check the success prompt in output
        // To Verify that the book has been borrowed from the user
        cy.get('#itemList').should('contain', 'Book successfully borrowed.');

        // Go to the returning books listing
        cy.get('#itemInput').type('2');
        cy.get('#submitButton').click();

        // Check the returning books prompt
        // So we can check if the book is listed with the right details
        cy.get('#itemList').should('contain', '3 | 1984 | George Orwell | Checked Out, Due: ' + returnDate);

        // Return the book
        cy.get('#itemInput').type('3');
        cy.get('#submitButton').click();
        cy.get('#itemInput').type('1');
        cy.get('#submitButton').click();

        // Check the success prompt in output
        // To Verify that the book has been successfully returned from the user
        cy.get('#itemList').should('contain', 'Book returned.');

        // Logout user from session
        // So that we can login as another user
        cy.get('#logoutButton').click();
        cy.url().should('include', '/login.html');

        // Login as charlie
        cy.login('charlie','pass789');

        // Check the notification at start in the prompt
        // So that we can make sure charlie received a notification, because he is the first in FIFO queue
        cy.get('#itemList').should('contain', 'NOTICE: The book 1984 is now available.');

        // Try to Borrow the same book
        cy.get('#itemInput').type('1');
        cy.get('#submitButton').click();

        // Check the borrowing books prompt
        // So we can check if the book is listed with the right details
        cy.get('#itemList').should('contain', '3 | 1984 | George Orwell | Available');

        // Try to borrow this book
        cy.get('#itemInput').type('3');
        cy.get('#submitButton').click();
        cy.get('#itemInput').type('1');
        cy.get('#submitButton').click();

        // Check the success prompt in output
        // To Verify that the book has been borrowed from the user
        cy.get('#itemList').should('contain', 'Book successfully borrowed.');

        // Logout user from session
        // So that the session is now cleared
        cy.get('#logoutButton').click();
        cy.url().should('include', '/login.html');
  });

  // Test Scenario 3
  it('should test the interaction between borrowing limits and holds', () => {

    // Login as alice
    cy.login('alice','pass123');

    // Go to the borrowing books screen
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the borrowing books number prompt
    // So we can check how many books the user has borrowed
    cy.get('#itemList').should('contain', 'Number of borrowed books: 0');

    // Borrow a book
    cy.get('#itemInput').type('5');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the success prompt in output
    // To Verify that the book has been borrowed from the user
    cy.get('#itemList').should('contain', 'Book successfully borrowed.');

    // Go to the borrowing books screen
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the borrowing books number prompt
    // So we can check how many books the user has borrowed
    cy.get('#itemList').should('contain', 'Number of borrowed books: 1');

    // Borrow a book
    cy.get('#itemInput').type('6');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the success prompt in output
    // To Verify that the book has been borrowed from the user
    cy.get('#itemList').should('contain', 'Book successfully borrowed.');

    // Go to the borrowing books screen
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the borrowing books number prompt
    // So we can check how many books the user has borrowed
    cy.get('#itemList').should('contain', 'Number of borrowed books: 2');

    // Borrow a book
    cy.get('#itemInput').type('7');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the success prompt in output
    // To Verify that the book has been borrowed from the user
    cy.get('#itemList').should('contain', 'Book successfully borrowed.');

    // Logout user from session
    // So that we can login as another user
    cy.get('#logoutButton').click();
    cy.url().should('include', '/login.html');

    // Login as Bob
    cy.login('bob','pass456');

    // Try to get a book on hold
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('6');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the prompt of the console
    // So that we can confirm the user put a book on hold at max capacity
    cy.get('#itemList').should('contain', 'Book on hold.');

    // Logout user from session
    // So that we can login as another user
    cy.get('#logoutButton').click();
    cy.url().should('include', '/login.html');

    // Login as alice
    cy.login('alice','pass123');

    // Check the prompt at start
    // So that we make sure alice has not received a notification
    cy.get('#itemList').should('not.contain', 'NOTICE: The book Harry Potter is now available.');

    // Go to the borrowing selection screen
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the borrowing books number prompt
    // So we can check that alice is at maximum capacity of borrowing
    cy.get('#itemList').should('contain', 'Number of borrowed books: 3');

    // The user will try to onhold the 4th book
    cy.get('#itemInput').type('8');
    cy.get('#submitButton').click();

    // Check the borrowing prompt
    // So we can check that the limit is reached but we can hold the book
    cy.get('#itemList').should('contain', 'Limit reached.');
    cy.get('#itemList').should('contain', 'Hold this book? (1=Yes, 2=No)');

    // Confirm the holding process
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the success prompt
    // So we can check that the book is now on hold from the user
    cy.get('#itemList').should('contain', 'Book on hold.');

    // Return one of the book
    cy.get('#itemInput').type('2');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('6');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the success prompt in output
    // To Verify that the book has been successfully returned from alice
    cy.get('#itemList').should('contain', 'Book returned.');

    // Now they can successfully borrow a new book
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the borrowing books number prompt
    // So we can check that alice is not at maximum capacity of borrowing
    cy.get('#itemList').should('contain', 'Number of borrowed books: 2');

    // Borrow one book
    cy.get('#itemInput').type('2');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the success prompt in output
    // To Verify that the book has been borrowed from the user
    cy.get('#itemList').should('contain', 'Book successfully borrowed.');

    // Logout user from session
    // So that we can login as another user
    cy.get('#logoutButton').click();
    cy.url().should('include', '/login.html');

    // Login as bob
    cy.login('bob','pass456');

    // Check the notification at start in the prompt
    // So that we can make sure bob received a notification, because he is the first in FIFO queue
    cy.get('#itemList').should('contain', 'NOTICE: The book Harry Potter is now available.');

    // Book Borrowing Process
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the borrowing books number prompt
    // So we can check that bob is not borrowing anything
    cy.get('#itemList').should('contain', 'Number of borrowed books: 0');

    // Borrow that book
    cy.get('#itemInput').type('6');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Check the success prompt in output
    // To Verify that the book has been borrowed from the user
    cy.get('#itemList').should('contain', 'Book successfully borrowed.');
  });
});

