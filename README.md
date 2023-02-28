#### My Personal Project
## Mineralogy ID Tool

The application will allow users to practice identifying minerals based on their physical properties. Users can keep
track of minerals they think they have learned, and minerals they think need more review. 
When the user starts the app, they will be prompted to choose between organizing their review and learned folders, 
entering minerals into their list, and studying minerals. The minerals that they can study will be the 
minerals on the review list that they created. 

**The properties each mineral will have associated with it are:**
- lab number (int)
- name (String)
- color (String)
- hardness (int)
- crystal system (String, 6 categories)

**User Stories:**
- As a user, I want to be able to add a mineral with a name, lab number, and the properties above to a list of minerals
- As a user, I want to be able to reveal individual properties of a mineral randomly selected from the list
- As a user, I want to be able to guess the name of a mineral based on revealed properties
- As a user, I want to be able to move minerals from a list of minerals to review to a list of learned minerals (and 
  back).
- As a user, when entering the crystal system of a mineral, I want the option to enter a letter code to a corresponding 
  crystal system instead of the full name
- As a user, when I select quit from the main menu, I want to be prompted to save my review folder and learned folder
  as they are, or the option not to save them
- As a user, when I open my application, I want to have the option to load the folders I have created previously