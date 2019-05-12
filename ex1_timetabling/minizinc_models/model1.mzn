%string: name;

% cardinality constants
int: nrWeeks;
int: nrDays;
int: slotsPerDay;

% soft constraint weight constants
int: time;
int: room;
int: distribution;
int: student;

output(["nrDays=\(nrDays)"])