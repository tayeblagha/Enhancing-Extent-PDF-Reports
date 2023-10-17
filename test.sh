#!/bin/bash

# Function to check the exit status of the previous command
check_exit_status() {
  if [ $? -eq 0 ]; then
    echo "Command executed successfully."
  else
    echo "Error executing command. Exiting..."
    exit 1
  fi
}

# Execute commands
mvn clean test -Dcucumber.filter.tags="@Client"
check_exit_status

mvn clean test -Dcucumber.filter.tags="@Libele"
check_exit_status

mvn clean test -Dcucumber.filter.tags="@Facture"
check_exit_status

mvn clean test -Dcucumber.filter.tags="@Delete"
check_exit_status
