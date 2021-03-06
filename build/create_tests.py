#!/usr/bin/python2
import os;


models=[
      ("account", "AccountBase", "AccountMirakel")
    , ("file", "FileBase", "FileMirakel")
    , ("list", "ListBase","ListMirakel")
#    , ("list", "ListBase","SpecialList")
    , ("recurring", "RecurringBase","Recurring")
    , ("semantic", "SemanticBase","Semantic")
    , ("tags", "TagBase","Tag")
    , ("task", "TaskBase","Task")
]
models_path="src/de/azapps/mirakel/model/"
tests_path="tests/"


def get_file_name(name,inst):
    return "model/" + models_path + name +"/" + inst +".java"

for (name,base,inst) in models:
    os.system("tests/scripts/generate_base_model_tests.py " + get_file_name(name,inst)+" "+ tests_path)        
    os.system("tests/scripts/generate_model_tests.py " + get_file_name(name,inst) + " " + get_file_name(name,base) + " "+tests_path)
    
os.system("./tests/scripts/generate_json_tests.py tests/scripts/tasks.json "+tests_path);
os.system("./tests/scripts/generate_recurrence_test.py "+tests_path)
#os.system("./main/tests/scripts/generate_tw_recurrence_tests.py "+tests_path)



