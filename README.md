#### My Personal Project
## Mineralogy ID Tool

The application will allow users to practice identifying minerals based on their physical 
properties.

When the user starts the game, they will be given the properties of a random, unknown mineral from their
list. They will then be able to reveal the name and formula of the mineral. If they feel like they know
the mineral, they can add it to their list of minerals that they don't need to practice more. If they don't
feel like they could have guessed the name of the mineral correctly before it was revealed, they can keep
the mineral on the list of minerals that need more practice. The minerals on the list that need more practice
will be eligible to be selected for the game. They will be able to add minerals back to the list of minerals
to practice when they need to review them. They will be able to sort the full list of minerals by the lab
number to target their studying.

**The properties each mineral will have associated with it are:**
- lab number (int)
- name (String)
- formula (String)
- color (List of String)
- streak (String)
- lustre (String)
- hardness (int "-" int)
- crystal system (String, 6 categories)
- cleavage (number of planes, angles)
- habit (String)

**User Stories:**
- As a user, I want to be able to add a mineral and its lab number, name, formula, and properties to a 
  list of minerals
- As a user, I want to be able to view the properties (but not the name, formula, or lab number) of a 
  mineral randomly selected from the list, then view the name, formula, and lab number when I request it
- As a user, I want to be able to move minerals from a to-practice list to a memorized list (and back).
- As a user, I want to be able to sort minerals by their lab number

