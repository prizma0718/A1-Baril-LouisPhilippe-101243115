describe('Library Book Management', () => {
  beforeEach(() => {
    // Reset items before each test
    cy.request('POST', 'http://localhost:3000/api/reset');
    cy.visit('http://localhost:3000');
  });

  // Test Scenario 1
  // TODO Check for Due Dates
  it('should test the basic borrow-return cycle with two users and one book', () => {

    // A borrowed book becomes unavailable to other users
    // The User must be able to login through the login form
    cy.get('#userInput').type('alice');
    cy.get('#passInput').type('pass123');

    // Submit the Credentials
    cy.get('#submitButton').click();

    // Check the Status of the book 
    cy.get('[data-testid="item"]').should('contain', 'Available');

    // Borrow a book
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('17');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Verify that the book has been borrowed from the user
    cy.get('[data-testid="item"]').should('contain', 'Book successfully borrowed.');

    // Logout user from session
    cy.get('#logoutButton').click();

    cy.get('#userInput').type('bob');
    cy.get('#passInput').type('pass456');

    // Click the submit button to send input
    cy.get('#submitButton').click();

    cy.get('[data-testid="item"]').should('contain', 'Checked Out');

    // Try to Borrow the same book
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('17');
    cy.get('#submitButton').click();

    // Verify the item appears in the list
    cy.get('[data-testid="item"]').should('contain', 'Book already borrowed.');

    // Logout user from session
    cy.get('#logoutButton').click();

    cy.get('#userInput').type('alice');
    cy.get('#passInput').type('pass123');

    // Click the submit button to send input
    cy.get('#submitButton').click();

    // Return the book
    cy.get('#itemInput').type('2');
    cy.get('#submitButton').click();

    // Check if the book details are in there
    cy.get('[data-testid="item"]').should('contain', '17.');

    cy.get('#itemInput').type('17');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Verify the item appears in the list
    cy.get('[data-testid="item"]').should('contain', 'Book returned.');

     // Logout user from session
    cy.get('#logoutButton').click();

    cy.get('#userInput').type('bob');
    cy.get('#passInput').type('pass456');
    cy.get('#submitButton').click();

    // Return the book
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('17');
    cy.get('#submitButton').click();
    cy.get('#itemInput').type('1');
    cy.get('#submitButton').click();

    // Verify that the book has been borrowed from the user
    cy.get('[data-testid="item"]').should('contain', 'Book successfully borrowed.');

  });

  // Test Scenario 2
  it('should add multiple items', () => {
    // Add first item
    cy.get('#itemInput').type('First Item');
    cy.get('#addButton').click();

    // Add second item
    cy.get('#itemInput').type('Second Item');
    cy.get('#addButton').click();

    // Verify both items are displayed
    cy.get('[data-testid="item"]').should('have.length', 2);
    cy.get('[data-testid="item"]').first().should('contain', 'First Item');
    cy.get('[data-testid="item"]').last().should('contain', 'Second Item');
  });

  // Test Scenario 3
  it('should clear input after adding item', () => {
    cy.get('#itemInput').type('Test Item');
    cy.get('#addButton').click();

    // Input should be empty after adding
    cy.get('#itemInput').should('have.value', '');
  });
});


describe('Start Flow Tests', () => {

  it('should start and display welcome message with username from server', () => {
    // Visit start page
    cy.visit('http://localhost:3000/start.html');

    // Type username
    cy.get('#nameInput').type('John Doe');

    // Click start button
    cy.get('#startButton').click();

    // Verify we're on the main page (URL changed)
    cy.url().should('include', '/index.html');

    // Verify welcome message contains the username (from server)
    cy.get('#welcomeMessage').should('contain', 'Welcome John Doe!');
  });

  it('should retrieve username from server on page load', () => {
    // First, start via API
    cy.request('POST', 'http://localhost:3000/api/start', { name: 'Jane Smith' });

    // Then visit index page directly
    cy.visit('http://localhost:3000/index.html');

    // Should show welcome message from server
    cy.get('#welcomeMessage').should('contain', 'Welcome Jane Smith!');
  });

  it('should add items after start page', () => {
    // Start first
    cy.visit('http://localhost:3000/start.html');
    cy.get('#nameInput').type('Jane Smith');
    cy.get('#startButton').click();

    // Verify we're on main page with welcome message
    cy.get('#welcomeMessage').should('contain', 'Welcome Jane Smith!');

    // Add an item
    cy.get('#itemInput').type('Test Item');
    cy.get('#addButton').click();

    // Verify item was added
    cy.get('[data-testid="item"]').should('contain', 'Test Item');
  });

  it('should show default message when no user is logged in', () => {
    // Visit index page without logging in
    cy.visit('http://localhost:3000/index.html');

    // Should show default message (no username)
    cy.get('#welcomeMessage').should('contain', 'Item Manager');
    cy.get('#welcomeMessage').should('not.contain', 'Welcome');
  });

  it('should not allow empty username', () => {
    cy.visit('http://localhost:3000/start.html');

    // Click start without entering name
    cy.get('#startButton').click();

    // Should still be on start page
    cy.url().should('include', '/start.html');
  });
});

describe('Demo with Custom Command', () => {
  beforeEach(() => {
    cy.request('POST', '/api/reset');
  });

  it('should use custom login command', () => {
    // Instead of writing all this:
    // cy.visit('/start.html');
    // cy.get('#nameInput').type('John Doe');
    // cy.get('#startButton').click();

    // Just do this:
    cy.login('John Doe');  // ← Custom command!

    cy.get('#welcomeMessage').should('contain', 'Welcome John Doe!');
    cy.get('#itemInput').type('Buy milk');
    cy.get('#addButton').click();
    cy.get('[data-testid="item"]').should('contain', 'Buy milk');
  });

  it('should login different users easily', () => {
    cy.login('Jane Smith');  // ← So easy!
    cy.get('#welcomeMessage').should('contain', 'Welcome Jane Smith!');
  });
});

