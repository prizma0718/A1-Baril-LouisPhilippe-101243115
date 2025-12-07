// Login Functionality
Cypress.Commands.add('login', (username, password) => {

  // Make sure that the login page is visited
  cy.visit('/login.html');

  // Make sure that the credentials are entered and submitted
  cy.get('#username').type(username);
  cy.get('#password').type(password);
  cy.get('#loginButton').click();

  // Make sure that the user was able to login successfully, checking the welcome message
  // The user is now logged in in the application with a welcome message prompted
  cy.get('#welcomeMessage').should('contain', 'Welcome ' + username);
});