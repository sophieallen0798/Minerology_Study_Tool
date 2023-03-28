#### My Personal Project
## Mineralogy ID Tool

The application will allow users to practice identifying minerals based on their physical properties. Users can keep
track of minerals they think they have learned, and minerals they think need more review. 
When the user starts the app, they will be prompted to choose between viewing and organizing their review and learned 
folders, entering minerals into their folder, loading or saving their folders, and studying minerals. The minerals that 
they can study will be the minerals on the review list and learned list that they created. 

**The properties each mineral will have associated with it are:**
- lab number
- name
- lustre
- color
- streak
- hardness
- specific gravity
- cleavage
- fracture
- habit
- crystal system
- other

**User Stories:**
- As a user, I want to be able to add a mineral with a name, lab number, and the properties above to a list of minerals
- As a user, I want to be able to reveal individual properties of a mineral randomly selected from the list. I want to
  be given all of the minerals in both my folders, but I want minerals I don't know as well to be given to me more
  frequently
- As a user, I want to be able to guess the name of a mineral based on revealed properties
- As a user, I want to be able to move minerals from a list of minerals to review to a list of learned minerals (and 
  back).
- As a user, I want to be able to save my folders after adding them, and to be prompted to save them on close
- As a user, when I open my application, I want to have the option to load the folders I have created previously


# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by selecting the menu option "Add Minerals" 
  which will allow you to add Minerals to the Review Folder. Fill out the text fields and press the "Add" button 
  at the bottom. This will create a mineral from the properties you entered and add that mineral to the Review Folder. 
  Pressing save from this window will save the current state of the folders.
- You can generate the second required action related to adding Xs to a Y by selecting the "View Review Folder" button 
  or the "View Learned Folder" button from the main menu. Then select the mineral you would like to move, and press the
  "Move Selected to Other Folder" button. You can also delete minerals using the same method but pressing 
  "Delete Selected" instead. You can sort any of the fields by clicking on the corresponding column heading.
- You can locate my visual component by running the app. The first frame that opens contains an image.
- You can save the state of my application by either saving from the add minerals frame as mentioned above, by
  selecting the "Save Folders" button from the main menu, or by selecting "Yes" when prompted on close. 
- You can reload the state of my application by selecting "Yes" when the program runs and prompts you to load folders
  or by selecting the "Load Folders" button from the main menu.