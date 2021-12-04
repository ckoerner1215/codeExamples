package com.bana.ck001.model;

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
             input0.setDescription("ck001");
             input0.setValue("ck001");
             inputs[0] = input0;
             
             Input input1 = new Input();
             input1.setKey("infile1");
             input1.setLabel("Input File 1");
             input1.setDescription(" The Unit 1 stdin file containing some stuff for ck001");
             input1.setValue("/app/prod/ck001/input.dat");
             inputs[1] = input1;

             Input input2 = new Input();
             input2.setKey("infile2");
             input2.setLabel("Input File 2");
             input2.setDescription(" The Unit 9 file containing latitude & longitude values");
             input2.setValue("/app/prod/ck001/llh.bin");
             inputs[2] = input2;

             Input input3 = new Input();
             input3.setKey("outputDirectory");
             input3.setLabel("Output Directory");
             input3.setDescription("This is where the output files will go");
             input3.setValue("/app/prod/ck001/OUTPUT");
             inputs[3] = input3;

             Input input4 = new Input();
             input4.setKey("scriptLocation");
             input4.setLabel("Script Location");
             input4.setDescription(" This is the location of the .ksh script file you would like to run");
             input4.setValue("/app/prod/ck001");
             inputs[4] = input4;

             Input input5 = new Input();
             input5.setKey("executableLocation");
             input5.setLabel(" Executable Location");
             input5.setDescription("This is the location of the executable for ck001");
             input5.setValue("/app/prod/ck001");
             inputs[5] = input5;
              
             this.inputs = inputs;
             
             RunInfo runInfo = new RunInfo();
             runInfo.setName("ck001");
             runInfo.setDescription("This is the description of ck001");
             runInfo.setInfile1("/app/prod/ck001/input.dat");
             runInfo.setInfile2("/app/prod/ck001/llh.bin"); 
             runInfo.setOutputDirectory("/app/prod/ck001/OUTPUT");
             runInfo.setScriptLocation("/app/prod/ck001");
             runInfo.setExecutableLocation("/app/prod/ck001");

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


