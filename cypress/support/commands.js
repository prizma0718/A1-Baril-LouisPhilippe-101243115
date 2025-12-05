Cypress.Commands.add('login', (name) => {
  cy.visit('/login.html');
  cy.get('#nameInput').type(name);
  cy.get('#startButton').click();
});