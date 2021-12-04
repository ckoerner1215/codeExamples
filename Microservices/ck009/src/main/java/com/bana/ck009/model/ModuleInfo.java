package com.bana.ck009.model;

import java.util.ArrayList;
import java.util.List;

public class ModuleInfo {

	private Input[] inputs;
	private RunInfo runInfo;


	public ModuleInfo() {
             Input inputs[] = new Input[5];

             Input input0 = new Input();
             input0.setKey("name");
             input0.setLabel("Name");
             input0.setDescription("ck009");
             input0.setValue("ck009");
             inputs[0] = input0;
             
             Input input1 = new Input();
             input1.setKey("infile1");
             input1.setLabel("Input File 1");
             input1.setDescription(" The Unit 1 stdin file containing some stuff for ck009");
             input1.setValue("/app/prod/ck009/input.dat");
             inputs[1] = input1;

             Input input2 = new Input();
             input2.setKey("outputDirectory");
             input2.setLabel("Output Directory");
             input2.setDescription("This is where the output files will go");
             input2.setValue("/app/prod/ck009/OUTPUT");
             inputs[2] = input2;

             Input input3 = new Input();
             input3.setKey("scriptLocation");
             input3.setLabel("Script Location");
             input3.setDescription(" This is the location of the .ksh script file you would like to run");
             input3.setValue("/app/prod/ck009");
             inputs[3] = input3;

             Input input4 = new Input();
             input4.setKey("executableLocation");
             input4.setLabel(" Executable Location");
             input4.setDescription("This is the location of the executable for ck009");
             input4.setValue("/app/prod/ck009");
             inputs[4] = input4;
              
             this.inputs = inputs;
             
             RunInfo runInfo = new RunInfo();
             runInfo.setName("ck009");
             runInfo.setDescription("This is the description of ck009");
             runInfo.setInfile1("/app/prod/ck009/input.dat");
             runInfo.setOutputDirectory("/app/prod/ck009/OUTPUT");
             runInfo.setScriptLocation("/app/prod/ck009");
             runInfo.setExecutableLocation("/app/prod/ck009");

             this.runInfo = runInfo;

	}

	public ModuleInfo(Input[] inputs, 
		RunInfo runInfo) {
		this.inputs = inputs;
		this.runInfo = runInfo;
	}

	public Input[] getInputs() {
		return inputs;
	}

	public RunInfo getRunInfo() {
		return runInfo;
	}

	public void setInputs(Input[] inputs) {
		this.inputs = inputs;
	}

	public void setRunInfo(RunInfo runInfo) {
		this.runInfo = runInfo;
	}

	@Override
	public String toString() {
		return "Module Info [ Inputs = " + inputs + ", Run Info =" + runInfo + "]";
	}
}


