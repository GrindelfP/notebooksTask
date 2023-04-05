# The Notebooks Task
The third task on Decision Theory Uni course. It is dedicated
to using multiple decision-making methods. The program is given set
of data: 12 alternatives of laptops to buy. The program should make a decision
which laptop to buy based on their criterion as well as the importance of each criteria.
## Methods of decision-making
1. Generating a Pareto's set, which is simply removing the worst alternatives (those which are worse than others by every criterion);
2. Narrowing a Pareto's set (this method cam be applied also to the "raw" set of every alternative) by comparing it to the worst acceptable criterion;
3. Sub-optimisation of the narrowed set: getting the best possible alternative by maximising one chosen criteria;
4. Lexicographical method which sorts criteria by importance and the result is the best alternative by first criteria (if not unique - second criteria etc.);
5. Method of combined criteria: criterion multiplied by certain coefficients are summed and the alternative, which gave best sum is the result.
## Project structure
Project is divided in several parts, which are:
- Data preprocessing: this part interprets data as object DataSet, which contains list of objects of Entry type, which contains information about each alternative;
- Decision-making: this part applies methods stated before to the data, to make a decision about which laptop is the best alternative. 
