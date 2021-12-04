package com.bana.ck100.model;

import java.util.ArrayList;
import java.util.List;

public class ModuleInfo {

	private Input[] inputs;
	private RunInfo runInfo;


	public ModuleInfo() {
             Input inputs[] = new Input[6];

             Input input0 = new Input();
             input0.setKey("name");
             input0.setLabel("Name");
             input0.setDescription("ck100");
             input0.setValue("ck100");
             inputs[0] = input0;
             
             Input input1 = new Input();
             input1.setKey("numberOfPoints");
             input1.setLabel("Number of Points");
             input1.setDescription(" The number of points you would like the average of");
             input1.setValue("3");
             inputs[1] = input1;

             Input input2 = new Input();
             input2.setKey("points");
             input2.setLabel("Points");
             input2.setDescription(" A list of numbers separated by spaces.  Can be +/- and/or integer/float.");
             input2.setValue("1 2 3");
             inputs[2] = input2;

             Input input5 = new Input();
             input5.setKey("outputDirectory");
             input5.setLabel("Output Directory");
             input5.setDescription("This is where the output files will go");
             input5.setValue("/app/prod/ck100/OUTPUT");
             inputs[5] = input5;

             Input input3 = new Input();
             input3.setKey("scriptLocation");
             input3.setLabel("Script Location");
             input3.setDescription(" This is the location of the .ksh script file you would like to run");
             input3.setValue("/app/prod/ck100");
             inputs[3] = input3;

             Input input4 = new Input();
             input4.setKey("executableLocation");
             input4.setLabel(" Executable Location");
             input4.setDescription("This is the location of the executable for ck100");
             input4.setValue("/app/prod/ck100");
             inputs[4] = input4;
              
             this.inputs = inputs;
             
             RunInfo runInfo = new RunInfo();
             runInfo.setName("ck100");
             runInfo.setDescription("This module calculates the average of a list of numbers");
             runInfo.setNumberOfPoints("3");
             runInfo.setPoints("1 2 3");
             runInfo.setOutputDirectory("/app/prod/ck100/OUTPUT");
             runInfo.setScriptLocation("/app/prod/ck100");
             runInfo.setExecutableLocation("/app/prod/ck100");

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


