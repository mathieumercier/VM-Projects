package fr.umlv.smalljs.grammar.parser;

import fr.umlv.smalljs.grammar.parser.NonTerminalEnum;
import fr.umlv.smalljs.grammar.parser.ProductionEnum;
import fr.umlv.smalljs.grammar.parser.TerminalEnum;
import fr.umlv.tatoo.runtime.parser.AcceptAction;
import fr.umlv.tatoo.runtime.parser.Action;
import fr.umlv.tatoo.runtime.parser.BranchAction;
import fr.umlv.tatoo.runtime.parser.ErrorAction;
import fr.umlv.tatoo.runtime.parser.ExitAction;
import fr.umlv.tatoo.runtime.parser.ParserTable;
import fr.umlv.tatoo.runtime.parser.ReduceAction;
import fr.umlv.tatoo.runtime.parser.ShiftAction;
import fr.umlv.tatoo.runtime.parser.StateMetadata;
import java.util.EnumMap;

/** 
 *  This class is generated - please do not edit it 
 */
public class ParserDataTable {
  private ParserDataTable() {
   accept = AcceptAction.<TerminalEnum,ProductionEnum,VersionEnum>getInstance();
   exit = ExitAction.<TerminalEnum,ProductionEnum,VersionEnum>getInstance();
    initinstr_star_0Gotoes();
    initinstrGotoes();
    initargsGotoes();
    initinitGotoes();
    initid_optional_4Gotoes();
    initid_star_5Gotoes();
    initeoiGotoes();
    initexprGotoes();
    initexpr_star_2Gotoes();
    initeol_optional_3Gotoes();
    initid_star_5_subGotoes();
    initinit_star_6Gotoes();
    initblockGotoes();
    initinstr_star_1Gotoes();
    initexpr_star_2_subGotoes();
    initscriptGotoes();
    initinit_star_6_subGotoes();
    reduceexpr_mul = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_mul,3,exprGotoes);
    reduceexpr_field_access = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_field_access,3,exprGotoes);
    reduceargs = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.args,1,argsGotoes);
    reduceexpr_sub = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_sub,3,exprGotoes);
    reduceeoi__semicolon = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.eoi__semicolon,1,eoiGotoes);
    reduceinstr_return = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.instr_return,3,instrGotoes);
    reduceexpr_le = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_le,3,exprGotoes);
    reduceexpr_method_call = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_method_call,6,exprGotoes);
    reduceexpr_ge = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_ge,3,exprGotoes);
    reduceeol_optional_3_empty = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.eol_optional_3_empty,0,eol_optional_3Gotoes);
    reduceinit_star_6_element = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.init_star_6_element,1,init_star_6_subGotoes);
    reduceinstr_if = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.instr_if,5,instrGotoes);
    reduceblock = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.block,3,blockGotoes);
    reduceid_optional_4_id = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.id_optional_4_id,1,id_optional_4Gotoes);
    reduceexpr_ne = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_ne,3,exprGotoes);
    reduceexpr_div = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_div,3,exprGotoes);
    reduceinstr_star_1_rec = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.instr_star_1_rec,2,instr_star_1Gotoes);
    reduceexpr_text = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_text,1,exprGotoes);
    reduceexpr_star_2_rec = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_star_2_rec,3,expr_star_2_subGotoes);
    reduceid_star_5_through = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.id_star_5_through,1,id_star_5Gotoes);
    reduceinit_star_6_through = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.init_star_6_through,1,init_star_6Gotoes);
    reduceid_optional_4_empty = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.id_optional_4_empty,0,id_optional_4Gotoes);
    reduceexpr_lt = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_lt,3,exprGotoes);
    reduceexpr_star_2_empty = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_star_2_empty,0,expr_star_2Gotoes);
    reduceinstr_star_1_empty = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.instr_star_1_empty,0,instr_star_1Gotoes);
    reduceinit = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.init,4,initGotoes);
    reduceexpr_rem = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_rem,3,exprGotoes);
    reduceexpr_eq = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_eq,3,exprGotoes);
    reduceinstr_while = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.instr_while,3,instrGotoes);
    reduceid_star_5_empty = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.id_star_5_empty,0,id_star_5Gotoes);
    reduceinstr_expr = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.instr_expr,2,instrGotoes);
    reduceinstr_star_0_empty = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.instr_star_0_empty,0,instr_star_0Gotoes);
    reduceexpr_numeric = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_numeric,1,exprGotoes);
    reduceinstr_var_assign = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.instr_var_assign,4,instrGotoes);
    reduceid_star_5_rec = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.id_star_5_rec,3,id_star_5_subGotoes);
    reduceexpr_star_2_through = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_star_2_through,1,expr_star_2Gotoes);
    reduceeoi__eol = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.eoi__eol,1,eoiGotoes);
    reduceinit_star_6_rec = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.init_star_6_rec,3,init_star_6_subGotoes);
    reduceexpr_gt = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_gt,3,exprGotoes);
    reduceexpr_function_creation = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_function_creation,6,exprGotoes);
    reduceeol_optional_3_eol = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.eol_optional_3_eol,1,eol_optional_3Gotoes);
    reduceid_star_5_element = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.id_star_5_element,1,id_star_5_subGotoes);
    reduceinstr_field_assign = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.instr_field_assign,6,instrGotoes);
    reduceinstr_star_0_rec = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.instr_star_0_rec,2,instr_star_0Gotoes);
    reduceexpr_new = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_new,3,exprGotoes);
    reduceexpr_add = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_add,3,exprGotoes);
    reduceexpr_parens = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_parens,3,exprGotoes);
    reducescript = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.script,1,scriptGotoes);
    reduceinstr_var_decl = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.instr_var_decl,5,instrGotoes);
    reduceinit_star_6_empty = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.init_star_6_empty,0,init_star_6Gotoes);
    reduceexpr_apply = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_apply,4,exprGotoes);
    reduceexpr_star_2_element = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_star_2_element,1,expr_star_2_subGotoes);
    reduceexpr_id = new ReduceAction<TerminalEnum,ProductionEnum,VersionEnum>(ProductionEnum.expr_id,1,exprGotoes);
    shift62 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(62);
    shift16 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(16);
    shift55 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(55);
    shift73 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(73);
    shift53 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(53);
    shift50 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(50);
    shift30 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(30);
    shift6 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(6);
    shift44 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(44);
    shift29 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(29);
    shift11 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(11);
    shift40 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(40);
    shift3 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(3);
    shift14 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(14);
    shift32 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(32);
    shift61 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(61);
    shift22 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(22);
    shift59 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(59);
    shift8 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(8);
    shift5 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(5);
    shift93 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(93);
    shift46 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(46);
    shift24 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(24);
    shift26 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(26);
    shift13 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(13);
    shift96 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(96);
    shift10 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(10);
    shift69 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(69);
    shift80 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(80);
    shift87 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(87);
    shift20 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(20);
    shift23 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(23);
    shift90 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(90);
    shift2 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(2);
    shift28 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(28);
    shift9 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(9);
    shift17 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(17);
    shift34 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(34);
    shift19 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(19);
    shift76 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(76);
    shift72 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(72);
    shift57 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(57);
    shift48 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(48);
    shift64 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(64);
    shift7 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(7);
    shift81 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(81);
    shift67 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(67);
    shift42 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(42);
    shift38 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(38);
    shift79 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(79);
    shift4 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(4);
    shift36 = new ShiftAction<TerminalEnum,ProductionEnum,VersionEnum>(36);
    error0 = new ErrorAction<TerminalEnum,ProductionEnum,VersionEnum>("parse error");
    branch0 = new BranchAction<TerminalEnum,ProductionEnum,VersionEnum>("parse error");
    initvarArray();
    initdotArray();
    initcolonArray();
    initeolArray();
    initgtArray();
    init_returnArray();
    initleArray();
    init_ifArray();
    initassignArray();
    init_whileArray();
    initintegerArray();
    initlparArray();
    initneArray();
    initidArray();
    initrcurlArray();
    initltArray();
    initcommaArray();
    init_elseArray();
    init__eof__Array();
    initlcurlArray();
    initaddArray();
    initgeArray();
    initeqArray();
    inittextArray();
    initsubArray();
    initdivArray();
    initmulArray();
    initrparArray();
    initremArray();
    initsemicolonArray();
    initfunctionArray();
    EnumMap<TerminalEnum,Action<TerminalEnum,ProductionEnum,VersionEnum>[]> tableMap =
      new EnumMap<TerminalEnum,Action<TerminalEnum,ProductionEnum,VersionEnum>[]>(TerminalEnum.class);
      
    tableMap.put(TerminalEnum.var,varArray);
    tableMap.put(TerminalEnum.dot,dotArray);
    tableMap.put(TerminalEnum.colon,colonArray);
    tableMap.put(TerminalEnum.eol,eolArray);
    tableMap.put(TerminalEnum.gt,gtArray);
    tableMap.put(TerminalEnum._return,_returnArray);
    tableMap.put(TerminalEnum.le,leArray);
    tableMap.put(TerminalEnum._if,_ifArray);
    tableMap.put(TerminalEnum.assign,assignArray);
    tableMap.put(TerminalEnum._while,_whileArray);
    tableMap.put(TerminalEnum.integer,integerArray);
    tableMap.put(TerminalEnum.lpar,lparArray);
    tableMap.put(TerminalEnum.ne,neArray);
    tableMap.put(TerminalEnum.id,idArray);
    tableMap.put(TerminalEnum.rcurl,rcurlArray);
    tableMap.put(TerminalEnum.lt,ltArray);
    tableMap.put(TerminalEnum.comma,commaArray);
    tableMap.put(TerminalEnum._else,_elseArray);
    tableMap.put(TerminalEnum.__eof__,__eof__Array);
    tableMap.put(TerminalEnum.lcurl,lcurlArray);
    tableMap.put(TerminalEnum.add,addArray);
    tableMap.put(TerminalEnum.ge,geArray);
    tableMap.put(TerminalEnum.eq,eqArray);
    tableMap.put(TerminalEnum.text,textArray);
    tableMap.put(TerminalEnum.sub,subArray);
    tableMap.put(TerminalEnum.div,divArray);
    tableMap.put(TerminalEnum.mul,mulArray);
    tableMap.put(TerminalEnum.rpar,rparArray);
    tableMap.put(TerminalEnum.rem,remArray);
    tableMap.put(TerminalEnum.semicolon,semicolonArray);
    tableMap.put(TerminalEnum.function,functionArray);
    initBranchArrayTable();
    
    StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>[] tableMetadata = createStateMetadataTable();
    
    EnumMap<NonTerminalEnum,Integer> tableStarts =
      new EnumMap<NonTerminalEnum,Integer>(NonTerminalEnum.class);
    tableStarts.put(NonTerminalEnum.script,0);
    table = new ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>(tableMap,branchArrayTable,tableMetadata,tableStarts,VersionEnum.values(),102,TerminalEnum.__eof__,null);
  } 

  // metadata aren't stored in local vars because it freak-out the register allocator of android
  @SuppressWarnings("unchecked")
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>[] createStateMetadataTable() {
        metadata0ge_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.ge,null);
    metadata0id_optional_4_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.id_optional_4,null);
    metadata0init_metadata0reduceinit_star_6_rec = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.init,reduceinit_star_6_rec);
    metadata0comma_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.comma,null);
    metadata0expr_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.expr,null);
    metadata0id_star_5_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.id_star_5,null);
    metadata0expr_star_2_metadata0reduceargs = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.expr_star_2,reduceargs);
    metadata0_return_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum._return,null);
    metadata0rpar_metadata0reduceexpr_method_call = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.rpar,reduceexpr_method_call);
    metadata0lcurl_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.lcurl,null);
    metadata0instr_star_0_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.instr_star_0,null);
    metadata0lpar_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.lpar,null);
    metadata0expr_star_2_sub_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.expr_star_2_sub,null);
    metadata0eoi_metadata0reduceinstr_var_decl = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.eoi,reduceinstr_var_decl);
    metadata0semicolon_metadata0reduceeoi__semicolon = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.semicolon,reduceeoi__semicolon);
    metadata0rcurl_metadata0reduceexpr_new = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.rcurl,reduceexpr_new);
    metadata0instr_metadata0reduceinstr_star_0_rec = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.instr,reduceinstr_star_0_rec);
    metadata0_while_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum._while,null);
    metadata0gt_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.gt,null);
    metadata0block_metadata0reduceexpr_function_creation = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.block,reduceexpr_function_creation);
    metadata0function_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.function,null);
    metadata0_else_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum._else,null);
    metadata0id_metadata0reduceid_optional_4_id = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.id,reduceid_optional_4_id);
    metadata0colon_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.colon,null);
    metadata0init_star_6_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.init_star_6,null);
    metadata0block_metadata0reduceinstr_if = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.block,reduceinstr_if);
    metadata0sub_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.sub,null);
    metadata0id_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.id,null);
    metadata0lcurl_metadata0reduceinstr_star_1_empty = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.lcurl,reduceinstr_star_1_empty);
    metadata0eoi_metadata0reduceinstr_return = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.eoi,reduceinstr_return);
    metadata0init_metadata0reduceinit_star_6_element = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.init,reduceinit_star_6_element);
    metadata0block_metadata0reduceinstr_while = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.block,reduceinstr_while);
    metadata0div_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.div,null);
    metadata0_if_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum._if,null);
    metadata0block_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.block,null);
    metadata0id_metadata0reduceexpr_id = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.id,reduceexpr_id);
    metadata0instr_star_1_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.instr_star_1,null);
    metadata0instr_metadata0reduceinstr_star_1_rec = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.instr,reduceinstr_star_1_rec);
    metadata0init_star_6_sub_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.init_star_6_sub,null);
    metadata0eoi_metadata0reduceinstr_field_assign = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.eoi,reduceinstr_field_assign);
    metadata0eoi_metadata0reduceinstr_var_assign = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.eoi,reduceinstr_var_assign);
    metadata0eol_metadata0reduceeol_optional_3_eol = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.eol,reduceeol_optional_3_eol);
    metadata0lt_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.lt,null);
    metadata0rpar_metadata0reduceexpr_apply = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.rpar,reduceexpr_apply);
    metadata0mul_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.mul,null);
    metadata0id_metadata0reduceid_star_5_rec = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.id,reduceid_star_5_rec);
    metadata0add_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.add,null);
    metadata0le_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.le,null);
    metadata0ne_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.ne,null);
    metadata0__eof___metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.__eof__,null);
    metadata0rem_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.rem,null);
    metadata0dot_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.dot,null);
    metadata0text_metadata0reduceexpr_text = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.text,reduceexpr_text);
    metadata0rcurl_metadata0reduceblock = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.rcurl,reduceblock);
    metadata0script_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.script,null);
    metadata0eoi_metadata0reduceinstr_expr = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.eoi,reduceinstr_expr);
    metadata0id_star_5_sub_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.id_star_5_sub,null);
    metadata0eq_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.eq,null);
    metadata0rpar_metadata0reduceexpr_parens = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.rpar,reduceexpr_parens);
    metadata0eol_optional_3_metadata0reduceinit = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.eol_optional_3,reduceinit);
    metadata0args_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(NonTerminalEnum.args,null);
    metadata0rpar_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.rpar,null);
    metadata0integer_metadata0reduceexpr_numeric = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.integer,reduceexpr_numeric);
    metadata0eol_metadata0reduceeoi__eol = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.eol,reduceeoi__eol);
    metadata0assign_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.assign,null);
    metadata0null_metadata0reduceinstr_star_0_empty = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithNonTerminal(null,reduceinstr_star_0_empty);
    metadata0var_metadata0null = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.var,null);
    metadata0id_metadata0reduceid_star_5_element = StateMetadata.<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>createAllVersionWithTerminal(TerminalEnum.id,reduceid_star_5_element);

    return (StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum>[])new StateMetadata<?,?,?,?>[]{metadata0null_metadata0reduceinstr_star_0_empty,metadata0instr_star_0_metadata0null,metadata0text_metadata0reduceexpr_text,metadata0_return_metadata0null,metadata0integer_metadata0reduceexpr_numeric,metadata0lpar_metadata0null,metadata0id_metadata0reduceexpr_id,metadata0lcurl_metadata0null,metadata0id_metadata0null,metadata0colon_metadata0null,metadata0function_metadata0null,metadata0id_metadata0reduceid_optional_4_id,metadata0id_optional_4_metadata0null,metadata0lpar_metadata0null,metadata0id_metadata0reduceid_star_5_element,metadata0id_star_5_sub_metadata0null,metadata0comma_metadata0null,metadata0id_metadata0reduceid_star_5_rec,metadata0id_star_5_metadata0null,metadata0rpar_metadata0null,metadata0lcurl_metadata0reduceinstr_star_1_empty,metadata0instr_star_1_metadata0null,metadata0var_metadata0null,metadata0id_metadata0null,metadata0assign_metadata0null,metadata0expr_metadata0null,metadata0lt_metadata0null,metadata0expr_metadata0null,metadata0dot_metadata0null,metadata0id_metadata0null,metadata0lpar_metadata0null,metadata0args_metadata0null,metadata0rpar_metadata0reduceexpr_method_call,metadata0expr_star_2_sub_metadata0null,metadata0comma_metadata0null,metadata0expr_metadata0null,metadata0add_metadata0null,metadata0expr_metadata0null,metadata0ge_metadata0null,metadata0expr_metadata0null,metadata0eq_metadata0null,metadata0expr_metadata0null,metadata0gt_metadata0null,metadata0expr_metadata0null,metadata0le_metadata0null,metadata0expr_metadata0null,metadata0sub_metadata0null,metadata0expr_metadata0null,metadata0lpar_metadata0null,metadata0args_metadata0null,metadata0rpar_metadata0reduceexpr_apply,metadata0expr_star_2_metadata0reduceargs,metadata0expr_metadata0null,metadata0ne_metadata0null,metadata0expr_metadata0null,metadata0div_metadata0null,metadata0expr_metadata0null,metadata0mul_metadata0null,metadata0expr_metadata0null,metadata0rem_metadata0null,metadata0expr_metadata0null,metadata0eol_metadata0reduceeoi__eol,metadata0semicolon_metadata0reduceeoi__semicolon,metadata0eoi_metadata0reduceinstr_var_decl,metadata0_if_metadata0null,metadata0expr_metadata0null,metadata0block_metadata0null,metadata0_else_metadata0null,metadata0block_metadata0reduceinstr_if,metadata0_while_metadata0null,metadata0expr_metadata0null,metadata0block_metadata0reduceinstr_while,metadata0id_metadata0null,metadata0assign_metadata0null,metadata0expr_metadata0null,metadata0eoi_metadata0reduceinstr_var_assign,metadata0rcurl_metadata0reduceblock,metadata0instr_metadata0reduceinstr_star_1_rec,metadata0expr_metadata0null,metadata0dot_metadata0null,metadata0id_metadata0null,metadata0assign_metadata0null,metadata0expr_metadata0null,metadata0eoi_metadata0reduceinstr_field_assign,metadata0eoi_metadata0reduceinstr_expr,metadata0block_metadata0reduceexpr_function_creation,metadata0expr_metadata0null,metadata0eol_metadata0reduceeol_optional_3_eol,metadata0eol_optional_3_metadata0reduceinit,metadata0init_star_6_metadata0null,metadata0rcurl_metadata0reduceexpr_new,metadata0init_metadata0reduceinit_star_6_element,metadata0init_star_6_sub_metadata0null,metadata0comma_metadata0null,metadata0init_metadata0reduceinit_star_6_rec,metadata0expr_metadata0null,metadata0rpar_metadata0reduceexpr_parens,metadata0expr_metadata0null,metadata0eoi_metadata0reduceinstr_return,metadata0instr_metadata0reduceinstr_star_0_rec,metadata0script_metadata0null,metadata0__eof___metadata0null};
  }

  
  private int[] instr_star_0Gotoes;

  private void initinstr_star_0Gotoes() {
    instr_star_0Gotoes = 
      new int[]{1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  }
  
  private int[] instrGotoes;

  private void initinstrGotoes() {
    instrGotoes = 
      new int[]{-1,99,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,77,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  }
  
  private int[] argsGotoes;

  private void initargsGotoes() {
    argsGotoes = 
      new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,31,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,49,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  }
  
  private int[] initGotoes;

  private void initinitGotoes() {
    initGotoes = 
      new int[]{-1,-1,-1,-1,-1,-1,-1,91,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,94,-1,-1,-1,-1,-1,-1,-1,-1};
  }
  
  private int[] id_optional_4Gotoes;

  private void initid_optional_4Gotoes() {
    id_optional_4Gotoes = 
      new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,12,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  }
  
  private int[] id_star_5Gotoes;

  private void initid_star_5Gotoes() {
    id_star_5Gotoes = 
      new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,18,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  }
  
  private int[] eoiGotoes;

  private void initeoiGotoes() {
    eoiGotoes = 
      new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,63,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,75,-1,-1,-1,84,-1,-1,-1,83,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,98,-1,-1,-1,-1};
  }
  
  private int[] exprGotoes;

  private void initexprGotoes() {
    exprGotoes = 
      new int[]{-1,78,-1,97,-1,95,-1,-1,-1,86,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,78,-1,-1,25,-1,27,-1,-1,-1,52,-1,-1,-1,35,-1,37,-1,39,-1,41,-1,43,-1,45,-1,47,-1,52,-1,-1,-1,-1,54,-1,56,-1,58,-1,60,-1,-1,-1,-1,65,-1,-1,-1,-1,70,-1,-1,-1,74,-1,-1,-1,-1,-1,-1,-1,82,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  }
  
  private int[] expr_star_2Gotoes;

  private void initexpr_star_2Gotoes() {
    expr_star_2Gotoes = 
      new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,51,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,51,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  }
  
  private int[] eol_optional_3Gotoes;

  private void initeol_optional_3Gotoes() {
    eol_optional_3Gotoes = 
      new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,88,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  }
  
  private int[] id_star_5_subGotoes;

  private void initid_star_5_subGotoes() {
    id_star_5_subGotoes = 
      new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,15,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  }
  
  private int[] init_star_6Gotoes;

  private void initinit_star_6Gotoes() {
    init_star_6Gotoes = 
      new int[]{-1,-1,-1,-1,-1,-1,-1,89,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  }
  
  private int[] blockGotoes;

  private void initblockGotoes() {
    blockGotoes = 
      new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,85,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,66,-1,68,-1,-1,71,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  }
  
  private int[] instr_star_1Gotoes;

  private void initinstr_star_1Gotoes() {
    instr_star_1Gotoes = 
      new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,21,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  }
  
  private int[] expr_star_2_subGotoes;

  private void initexpr_star_2_subGotoes() {
    expr_star_2_subGotoes = 
      new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,33,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,33,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  }
  
  private int[] scriptGotoes;

  private void initscriptGotoes() {
    scriptGotoes = 
      new int[]{100,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  }
  
  private int[] init_star_6_subGotoes;

  private void initinit_star_6_subGotoes() {
    init_star_6_subGotoes = 
      new int[]{-1,-1,-1,-1,-1,-1,-1,92,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  }

  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] varArray;
  @SuppressWarnings("unchecked")
  private void initvarArray() {
    varArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceinstr_star_0_empty,shift22,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_star_1_empty,shift22,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceeoi__eol,reduceeoi__semicolon,reduceinstr_var_decl,branch0,branch0,branch0,branch0,reduceinstr_if,branch0,branch0,reduceinstr_while,branch0,branch0,branch0,reduceinstr_var_assign,reduceblock,reduceinstr_star_1_rec,branch0,branch0,branch0,branch0,branch0,reduceinstr_field_assign,reduceinstr_expr,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_return,reduceinstr_star_0_rec,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] dotArray;
  @SuppressWarnings("unchecked")
  private void initdotArray() {
    dotArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift28,branch0,shift28,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,branch0,branch0,shift28,branch0,shift28,branch0,shift28,branch0,shift28,branch0,shift28,branch0,shift28,branch0,shift28,branch0,branch0,reduceexpr_apply,branch0,shift28,branch0,shift28,branch0,shift28,branch0,shift28,branch0,shift28,branch0,branch0,branch0,branch0,shift28,branch0,branch0,branch0,branch0,shift28,branch0,reduceexpr_id,branch0,shift28,branch0,reduceblock,branch0,shift79,branch0,reduceexpr_field_access,branch0,shift28,branch0,branch0,reduceexpr_function_creation,shift28,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,shift28,reduceexpr_parens,shift28,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] colonArray;
  @SuppressWarnings("unchecked")
  private void initcolonArray() {
    colonArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift9,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] eolArray;
  @SuppressWarnings("unchecked")
  private void initeolArray() {
    eolArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift61,branch0,reduceexpr_lt,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,branch0,branch0,branch0,branch0,reduceexpr_add,branch0,reduceexpr_ge,branch0,reduceexpr_eq,branch0,reduceexpr_gt,branch0,reduceexpr_le,branch0,reduceexpr_sub,branch0,branch0,reduceexpr_apply,branch0,branch0,branch0,reduceexpr_ne,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceexpr_id,branch0,shift61,branch0,reduceblock,branch0,shift61,branch0,reduceexpr_field_access,branch0,shift61,branch0,branch0,reduceexpr_function_creation,shift87,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,branch0,reduceexpr_parens,shift61,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] gtArray;
  @SuppressWarnings("unchecked")
  private void initgtArray() {
    gtArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift42,branch0,reduceexpr_lt,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,branch0,branch0,shift42,branch0,reduceexpr_add,branch0,reduceexpr_ge,branch0,reduceexpr_eq,branch0,reduceexpr_gt,branch0,reduceexpr_le,branch0,reduceexpr_sub,branch0,branch0,reduceexpr_apply,branch0,shift42,branch0,reduceexpr_ne,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,branch0,branch0,branch0,branch0,shift42,branch0,branch0,branch0,branch0,shift42,branch0,reduceexpr_id,branch0,shift42,branch0,reduceblock,branch0,shift42,branch0,reduceexpr_field_access,branch0,shift42,branch0,branch0,reduceexpr_function_creation,shift42,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,shift42,reduceexpr_parens,shift42,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] _returnArray;
  @SuppressWarnings("unchecked")
  private void init_returnArray() {
    _returnArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceinstr_star_0_empty,shift3,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_star_1_empty,shift3,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceeoi__eol,reduceeoi__semicolon,reduceinstr_var_decl,branch0,branch0,branch0,branch0,reduceinstr_if,branch0,branch0,reduceinstr_while,branch0,branch0,branch0,reduceinstr_var_assign,reduceblock,reduceinstr_star_1_rec,branch0,branch0,branch0,branch0,branch0,reduceinstr_field_assign,reduceinstr_expr,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_return,reduceinstr_star_0_rec,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] leArray;
  @SuppressWarnings("unchecked")
  private void initleArray() {
    leArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift44,branch0,reduceexpr_lt,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,branch0,branch0,shift44,branch0,reduceexpr_add,branch0,reduceexpr_ge,branch0,reduceexpr_eq,branch0,reduceexpr_gt,branch0,reduceexpr_le,branch0,reduceexpr_sub,branch0,branch0,reduceexpr_apply,branch0,shift44,branch0,reduceexpr_ne,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,branch0,branch0,branch0,branch0,shift44,branch0,branch0,branch0,branch0,shift44,branch0,reduceexpr_id,branch0,shift44,branch0,reduceblock,branch0,shift44,branch0,reduceexpr_field_access,branch0,shift44,branch0,branch0,reduceexpr_function_creation,shift44,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,shift44,reduceexpr_parens,shift44,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] _ifArray;
  @SuppressWarnings("unchecked")
  private void init_ifArray() {
    _ifArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceinstr_star_0_empty,shift64,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_star_1_empty,shift64,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceeoi__eol,reduceeoi__semicolon,reduceinstr_var_decl,branch0,branch0,branch0,branch0,reduceinstr_if,branch0,branch0,reduceinstr_while,branch0,branch0,branch0,reduceinstr_var_assign,reduceblock,reduceinstr_star_1_rec,branch0,branch0,branch0,branch0,branch0,reduceinstr_field_assign,reduceinstr_expr,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_return,reduceinstr_star_0_rec,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] assignArray;
  @SuppressWarnings("unchecked")
  private void initassignArray() {
    assignArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift24,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift73,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift81,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] _whileArray;
  @SuppressWarnings("unchecked")
  private void init_whileArray() {
    _whileArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceinstr_star_0_empty,shift69,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_star_1_empty,shift69,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceeoi__eol,reduceeoi__semicolon,reduceinstr_var_decl,branch0,branch0,branch0,branch0,reduceinstr_if,branch0,branch0,reduceinstr_while,branch0,branch0,branch0,reduceinstr_var_assign,reduceblock,reduceinstr_star_1_rec,branch0,branch0,branch0,branch0,branch0,reduceinstr_field_assign,reduceinstr_expr,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_return,reduceinstr_star_0_rec,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] integerArray;
  @SuppressWarnings("unchecked")
  private void initintegerArray() {
    integerArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceinstr_star_0_empty,shift4,branch0,shift4,branch0,shift4,branch0,branch0,branch0,shift4,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_star_1_empty,shift4,branch0,branch0,shift4,branch0,shift4,branch0,branch0,branch0,shift4,branch0,branch0,branch0,shift4,branch0,shift4,branch0,shift4,branch0,shift4,branch0,shift4,branch0,shift4,branch0,shift4,branch0,shift4,branch0,branch0,branch0,branch0,shift4,branch0,shift4,branch0,shift4,branch0,shift4,branch0,reduceeoi__eol,reduceeoi__semicolon,reduceinstr_var_decl,shift4,branch0,branch0,branch0,reduceinstr_if,shift4,branch0,reduceinstr_while,branch0,shift4,branch0,reduceinstr_var_assign,reduceblock,reduceinstr_star_1_rec,branch0,branch0,branch0,shift4,branch0,reduceinstr_field_assign,reduceinstr_expr,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_return,reduceinstr_star_0_rec,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] lparArray;
  @SuppressWarnings("unchecked")
  private void initlparArray() {
    lparArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceinstr_star_0_empty,shift5,reduceexpr_text,shift5,reduceexpr_numeric,shift5,reduceexpr_id,branch0,branch0,shift5,reduceid_optional_4_empty,reduceid_optional_4_id,shift13,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_star_1_empty,shift5,branch0,branch0,shift5,shift48,shift5,shift48,branch0,shift30,shift5,branch0,reduceexpr_method_call,branch0,shift5,shift48,shift5,shift48,shift5,shift48,shift5,shift48,shift5,shift48,shift5,shift48,shift5,shift48,shift5,branch0,reduceexpr_apply,branch0,shift48,shift5,shift48,shift5,shift48,shift5,shift48,shift5,shift48,reduceeoi__eol,reduceeoi__semicolon,reduceinstr_var_decl,shift5,shift48,branch0,branch0,reduceinstr_if,shift5,shift48,reduceinstr_while,reduceexpr_id,shift5,shift48,reduceinstr_var_assign,reduceblock,reduceinstr_star_1_rec,shift48,branch0,shift30,shift5,shift48,reduceinstr_field_assign,reduceinstr_expr,reduceexpr_function_creation,shift48,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,shift48,reduceexpr_parens,shift48,reduceinstr_return,reduceinstr_star_0_rec,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] neArray;
  @SuppressWarnings("unchecked")
  private void initneArray() {
    neArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift53,branch0,reduceexpr_lt,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,branch0,branch0,shift53,branch0,reduceexpr_add,branch0,reduceexpr_ge,branch0,reduceexpr_eq,branch0,reduceexpr_gt,branch0,reduceexpr_le,branch0,reduceexpr_sub,branch0,branch0,reduceexpr_apply,branch0,shift53,branch0,reduceexpr_ne,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,branch0,branch0,branch0,branch0,shift53,branch0,branch0,branch0,branch0,shift53,branch0,reduceexpr_id,branch0,shift53,branch0,reduceblock,branch0,shift53,branch0,reduceexpr_field_access,branch0,shift53,branch0,branch0,reduceexpr_function_creation,shift53,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,shift53,reduceexpr_parens,shift53,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] idArray;
  @SuppressWarnings("unchecked")
  private void initidArray() {
    idArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceinstr_star_0_empty,shift72,branch0,shift6,branch0,shift6,branch0,shift8,branch0,shift6,shift11,branch0,branch0,shift14,branch0,branch0,shift17,branch0,branch0,branch0,reduceinstr_star_1_empty,shift72,shift23,branch0,shift6,branch0,shift6,branch0,shift29,branch0,shift6,branch0,branch0,branch0,shift6,branch0,shift6,branch0,shift6,branch0,shift6,branch0,shift6,branch0,shift6,branch0,shift6,branch0,shift6,branch0,branch0,branch0,branch0,shift6,branch0,shift6,branch0,shift6,branch0,shift6,branch0,reduceeoi__eol,reduceeoi__semicolon,reduceinstr_var_decl,shift6,branch0,branch0,branch0,reduceinstr_if,shift6,branch0,reduceinstr_while,branch0,shift6,branch0,reduceinstr_var_assign,reduceblock,reduceinstr_star_1_rec,branch0,shift80,branch0,shift6,branch0,reduceinstr_field_assign,reduceinstr_expr,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift8,branch0,branch0,branch0,branch0,reduceinstr_return,reduceinstr_star_0_rec,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] rcurlArray;
  @SuppressWarnings("unchecked")
  private void initrcurlArray() {
    rcurlArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,reduceinit_star_6_empty,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_star_1_empty,shift76,branch0,branch0,branch0,branch0,branch0,reduceexpr_lt,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,branch0,branch0,branch0,branch0,reduceexpr_add,branch0,reduceexpr_ge,branch0,reduceexpr_eq,branch0,reduceexpr_gt,branch0,reduceexpr_le,branch0,reduceexpr_sub,branch0,branch0,reduceexpr_apply,branch0,branch0,branch0,reduceexpr_ne,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,reduceeoi__eol,reduceeoi__semicolon,reduceinstr_var_decl,branch0,branch0,branch0,branch0,reduceinstr_if,branch0,branch0,reduceinstr_while,branch0,branch0,branch0,reduceinstr_var_assign,reduceblock,reduceinstr_star_1_rec,branch0,branch0,branch0,branch0,branch0,reduceinstr_field_assign,reduceinstr_expr,reduceexpr_function_creation,reduceeol_optional_3_empty,reduceeol_optional_3_eol,reduceinit,shift90,reduceexpr_new,reduceinit_star_6_element,reduceinit_star_6_through,branch0,reduceinit_star_6_rec,branch0,reduceexpr_parens,branch0,reduceinstr_return,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] ltArray;
  @SuppressWarnings("unchecked")
  private void initltArray() {
    ltArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift26,branch0,reduceexpr_lt,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,branch0,branch0,shift26,branch0,reduceexpr_add,branch0,reduceexpr_ge,branch0,reduceexpr_eq,branch0,reduceexpr_gt,branch0,reduceexpr_le,branch0,reduceexpr_sub,branch0,branch0,reduceexpr_apply,branch0,shift26,branch0,reduceexpr_ne,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,branch0,branch0,branch0,branch0,shift26,branch0,branch0,branch0,branch0,shift26,branch0,reduceexpr_id,branch0,shift26,branch0,reduceblock,branch0,shift26,branch0,reduceexpr_field_access,branch0,shift26,branch0,branch0,reduceexpr_function_creation,shift26,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,shift26,reduceexpr_parens,shift26,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] commaArray;
  @SuppressWarnings("unchecked")
  private void initcommaArray() {
    commaArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceid_star_5_element,shift16,branch0,reduceid_star_5_rec,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceexpr_lt,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,shift34,branch0,reduceexpr_star_2_rec,branch0,reduceexpr_add,branch0,reduceexpr_ge,branch0,reduceexpr_eq,branch0,reduceexpr_gt,branch0,reduceexpr_le,branch0,reduceexpr_sub,branch0,branch0,reduceexpr_apply,branch0,reduceexpr_star_2_element,branch0,reduceexpr_ne,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceblock,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceexpr_function_creation,reduceeol_optional_3_empty,reduceeol_optional_3_eol,reduceinit,branch0,reduceexpr_new,reduceinit_star_6_element,shift93,branch0,reduceinit_star_6_rec,branch0,reduceexpr_parens,branch0,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] _elseArray;
  @SuppressWarnings("unchecked")
  private void init_elseArray() {
    _elseArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift67,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceblock,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] __eof__Array;
  @SuppressWarnings("unchecked")
  private void init__eof__Array() {
    __eof__Array=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceinstr_star_0_empty,reducescript,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceeoi__eol,reduceeoi__semicolon,reduceinstr_var_decl,branch0,branch0,branch0,branch0,reduceinstr_if,branch0,branch0,reduceinstr_while,branch0,branch0,branch0,reduceinstr_var_assign,reduceblock,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_field_assign,reduceinstr_expr,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_return,reduceinstr_star_0_rec,accept,accept};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] lcurlArray;
  @SuppressWarnings("unchecked")
  private void initlcurlArray() {
    lcurlArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceinstr_star_0_empty,shift7,reduceexpr_text,shift7,reduceexpr_numeric,shift7,reduceexpr_id,branch0,branch0,shift7,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift20,reduceinstr_star_1_empty,shift7,branch0,branch0,shift7,branch0,shift7,reduceexpr_lt,branch0,reduceexpr_field_access,shift7,branch0,reduceexpr_method_call,branch0,shift7,branch0,shift7,reduceexpr_add,shift7,reduceexpr_ge,shift7,reduceexpr_eq,shift7,reduceexpr_gt,shift7,reduceexpr_le,shift7,reduceexpr_sub,shift7,branch0,reduceexpr_apply,branch0,branch0,shift7,reduceexpr_ne,shift7,reduceexpr_div,shift7,reduceexpr_mul,shift7,reduceexpr_rem,reduceeoi__eol,reduceeoi__semicolon,reduceinstr_var_decl,shift7,shift20,branch0,shift20,reduceinstr_if,shift7,shift20,reduceinstr_while,branch0,shift7,branch0,reduceinstr_var_assign,reduceblock,reduceinstr_star_1_rec,branch0,branch0,branch0,shift7,branch0,reduceinstr_field_assign,reduceinstr_expr,reduceexpr_function_creation,branch0,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,branch0,reduceexpr_parens,branch0,reduceinstr_return,reduceinstr_star_0_rec,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] addArray;
  @SuppressWarnings("unchecked")
  private void initaddArray() {
    addArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift36,branch0,shift36,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,branch0,branch0,shift36,branch0,reduceexpr_add,branch0,shift36,branch0,shift36,branch0,shift36,branch0,shift36,branch0,reduceexpr_sub,branch0,branch0,reduceexpr_apply,branch0,shift36,branch0,shift36,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,branch0,branch0,branch0,branch0,shift36,branch0,branch0,branch0,branch0,shift36,branch0,reduceexpr_id,branch0,shift36,branch0,reduceblock,branch0,shift36,branch0,reduceexpr_field_access,branch0,shift36,branch0,branch0,reduceexpr_function_creation,shift36,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,shift36,reduceexpr_parens,shift36,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] geArray;
  @SuppressWarnings("unchecked")
  private void initgeArray() {
    geArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift38,branch0,reduceexpr_lt,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,branch0,branch0,shift38,branch0,reduceexpr_add,branch0,reduceexpr_ge,branch0,reduceexpr_eq,branch0,reduceexpr_gt,branch0,reduceexpr_le,branch0,reduceexpr_sub,branch0,branch0,reduceexpr_apply,branch0,shift38,branch0,reduceexpr_ne,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,branch0,branch0,branch0,branch0,shift38,branch0,branch0,branch0,branch0,shift38,branch0,reduceexpr_id,branch0,shift38,branch0,reduceblock,branch0,shift38,branch0,reduceexpr_field_access,branch0,shift38,branch0,branch0,reduceexpr_function_creation,shift38,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,shift38,reduceexpr_parens,shift38,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] eqArray;
  @SuppressWarnings("unchecked")
  private void initeqArray() {
    eqArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift40,branch0,reduceexpr_lt,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,branch0,branch0,shift40,branch0,reduceexpr_add,branch0,reduceexpr_ge,branch0,reduceexpr_eq,branch0,reduceexpr_gt,branch0,reduceexpr_le,branch0,reduceexpr_sub,branch0,branch0,reduceexpr_apply,branch0,shift40,branch0,reduceexpr_ne,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,branch0,branch0,branch0,branch0,shift40,branch0,branch0,branch0,branch0,shift40,branch0,reduceexpr_id,branch0,shift40,branch0,reduceblock,branch0,shift40,branch0,reduceexpr_field_access,branch0,shift40,branch0,branch0,reduceexpr_function_creation,shift40,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,shift40,reduceexpr_parens,shift40,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] textArray;
  @SuppressWarnings("unchecked")
  private void inittextArray() {
    textArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceinstr_star_0_empty,shift2,branch0,shift2,branch0,shift2,branch0,branch0,branch0,shift2,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_star_1_empty,shift2,branch0,branch0,shift2,branch0,shift2,branch0,branch0,branch0,shift2,branch0,branch0,branch0,shift2,branch0,shift2,branch0,shift2,branch0,shift2,branch0,shift2,branch0,shift2,branch0,shift2,branch0,shift2,branch0,branch0,branch0,branch0,shift2,branch0,shift2,branch0,shift2,branch0,shift2,branch0,reduceeoi__eol,reduceeoi__semicolon,reduceinstr_var_decl,shift2,branch0,branch0,branch0,reduceinstr_if,shift2,branch0,reduceinstr_while,branch0,shift2,branch0,reduceinstr_var_assign,reduceblock,reduceinstr_star_1_rec,branch0,branch0,branch0,shift2,branch0,reduceinstr_field_assign,reduceinstr_expr,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_return,reduceinstr_star_0_rec,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] subArray;
  @SuppressWarnings("unchecked")
  private void initsubArray() {
    subArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift46,branch0,shift46,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,branch0,branch0,shift46,branch0,reduceexpr_add,branch0,shift46,branch0,shift46,branch0,shift46,branch0,shift46,branch0,reduceexpr_sub,branch0,branch0,reduceexpr_apply,branch0,shift46,branch0,shift46,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,branch0,branch0,branch0,branch0,shift46,branch0,branch0,branch0,branch0,shift46,branch0,reduceexpr_id,branch0,shift46,branch0,reduceblock,branch0,shift46,branch0,reduceexpr_field_access,branch0,shift46,branch0,branch0,reduceexpr_function_creation,shift46,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,shift46,reduceexpr_parens,shift46,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] divArray;
  @SuppressWarnings("unchecked")
  private void initdivArray() {
    divArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift55,branch0,shift55,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,branch0,branch0,shift55,branch0,shift55,branch0,shift55,branch0,shift55,branch0,shift55,branch0,shift55,branch0,shift55,branch0,branch0,reduceexpr_apply,branch0,shift55,branch0,shift55,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,branch0,branch0,branch0,branch0,shift55,branch0,branch0,branch0,branch0,shift55,branch0,reduceexpr_id,branch0,shift55,branch0,reduceblock,branch0,shift55,branch0,reduceexpr_field_access,branch0,shift55,branch0,branch0,reduceexpr_function_creation,shift55,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,shift55,reduceexpr_parens,shift55,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] mulArray;
  @SuppressWarnings("unchecked")
  private void initmulArray() {
    mulArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift57,branch0,shift57,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,branch0,branch0,shift57,branch0,shift57,branch0,shift57,branch0,shift57,branch0,shift57,branch0,shift57,branch0,shift57,branch0,branch0,reduceexpr_apply,branch0,shift57,branch0,shift57,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,branch0,branch0,branch0,branch0,shift57,branch0,branch0,branch0,branch0,shift57,branch0,reduceexpr_id,branch0,shift57,branch0,reduceblock,branch0,shift57,branch0,reduceexpr_field_access,branch0,shift57,branch0,branch0,reduceexpr_function_creation,shift57,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,shift57,reduceexpr_parens,shift57,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] rparArray;
  @SuppressWarnings("unchecked")
  private void initrparArray() {
    rparArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,reduceid_star_5_empty,reduceid_star_5_element,reduceid_star_5_through,branch0,reduceid_star_5_rec,shift19,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceexpr_lt,branch0,reduceexpr_field_access,reduceexpr_star_2_empty,shift32,reduceexpr_method_call,reduceexpr_star_2_through,branch0,reduceexpr_star_2_rec,branch0,reduceexpr_add,branch0,reduceexpr_ge,branch0,reduceexpr_eq,branch0,reduceexpr_gt,branch0,reduceexpr_le,branch0,reduceexpr_sub,reduceexpr_star_2_empty,shift50,reduceexpr_apply,reduceargs,reduceexpr_star_2_element,branch0,reduceexpr_ne,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceblock,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceexpr_function_creation,branch0,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,shift96,reduceexpr_parens,branch0,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] remArray;
  @SuppressWarnings("unchecked")
  private void initremArray() {
    remArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift59,branch0,shift59,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,branch0,branch0,shift59,branch0,shift59,branch0,shift59,branch0,shift59,branch0,shift59,branch0,shift59,branch0,shift59,branch0,branch0,reduceexpr_apply,branch0,shift59,branch0,shift59,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,branch0,branch0,branch0,branch0,shift59,branch0,branch0,branch0,branch0,shift59,branch0,reduceexpr_id,branch0,shift59,branch0,reduceblock,branch0,shift59,branch0,reduceexpr_field_access,branch0,shift59,branch0,branch0,reduceexpr_function_creation,shift59,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,shift59,reduceexpr_parens,shift59,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] semicolonArray;
  @SuppressWarnings("unchecked")
  private void initsemicolonArray() {
    semicolonArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{branch0,branch0,reduceexpr_text,branch0,reduceexpr_numeric,branch0,reduceexpr_id,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,shift62,branch0,reduceexpr_lt,branch0,reduceexpr_field_access,branch0,branch0,reduceexpr_method_call,branch0,branch0,branch0,branch0,reduceexpr_add,branch0,reduceexpr_ge,branch0,reduceexpr_eq,branch0,reduceexpr_gt,branch0,reduceexpr_le,branch0,reduceexpr_sub,branch0,branch0,reduceexpr_apply,branch0,branch0,branch0,reduceexpr_ne,branch0,reduceexpr_div,branch0,reduceexpr_mul,branch0,reduceexpr_rem,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceexpr_id,branch0,shift62,branch0,reduceblock,branch0,shift62,branch0,reduceexpr_field_access,branch0,shift62,branch0,branch0,reduceexpr_function_creation,branch0,branch0,branch0,branch0,reduceexpr_new,branch0,branch0,branch0,branch0,branch0,reduceexpr_parens,shift62,branch0,branch0,branch0,branch0};
  }
  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] functionArray;
  @SuppressWarnings("unchecked")
  private void initfunctionArray() {
    functionArray=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceinstr_star_0_empty,shift10,branch0,shift10,branch0,shift10,branch0,branch0,branch0,shift10,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_star_1_empty,shift10,branch0,branch0,shift10,branch0,shift10,branch0,branch0,branch0,shift10,branch0,branch0,branch0,shift10,branch0,shift10,branch0,shift10,branch0,shift10,branch0,shift10,branch0,shift10,branch0,shift10,branch0,shift10,branch0,branch0,branch0,branch0,shift10,branch0,shift10,branch0,shift10,branch0,shift10,branch0,reduceeoi__eol,reduceeoi__semicolon,reduceinstr_var_decl,shift10,branch0,branch0,branch0,reduceinstr_if,shift10,branch0,reduceinstr_while,branch0,shift10,branch0,reduceinstr_var_assign,reduceblock,reduceinstr_star_1_rec,branch0,branch0,branch0,shift10,branch0,reduceinstr_field_assign,reduceinstr_expr,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,branch0,reduceinstr_return,reduceinstr_star_0_rec,branch0,branch0};
  }

  private Action<TerminalEnum,ProductionEnum,VersionEnum>[] branchArrayTable;
  @SuppressWarnings("unchecked")
  private void initBranchArrayTable() {
    branchArrayTable=(Action<TerminalEnum,ProductionEnum,VersionEnum>[])new Action<?,?,?>[]{reduceinstr_star_0_empty,reducescript,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,reduceeoi__eol,reduceeoi__semicolon,reduceinstr_var_decl,error0,error0,error0,error0,reduceinstr_if,error0,error0,reduceinstr_while,error0,error0,error0,reduceinstr_var_assign,reduceblock,error0,error0,error0,error0,error0,error0,reduceinstr_field_assign,reduceinstr_expr,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,error0,reduceinstr_return,reduceinstr_star_0_rec,exit,exit};
  }

  private final ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> table;
  
  public static final ParserTable<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> createTable() {
    return new ParserDataTable().table;
  }

  private final AcceptAction<TerminalEnum,ProductionEnum,VersionEnum> accept;
  private final ExitAction<TerminalEnum,ProductionEnum,VersionEnum> exit;

  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_mul;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_field_access;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceargs;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_sub;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceeoi__semicolon;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinstr_return;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_le;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_method_call;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_ge;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceeol_optional_3_empty;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinit_star_6_element;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinstr_if;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceblock;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceid_optional_4_id;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_ne;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_div;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinstr_star_1_rec;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_text;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_star_2_rec;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceid_star_5_through;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinit_star_6_through;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceid_optional_4_empty;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_lt;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_star_2_empty;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinstr_star_1_empty;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinit;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_rem;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_eq;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinstr_while;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceid_star_5_empty;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinstr_expr;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinstr_star_0_empty;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_numeric;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinstr_var_assign;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceid_star_5_rec;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_star_2_through;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceeoi__eol;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinit_star_6_rec;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_gt;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_function_creation;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceeol_optional_3_eol;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceid_star_5_element;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinstr_field_assign;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinstr_star_0_rec;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_new;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_add;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_parens;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reducescript;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinstr_var_decl;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceinit_star_6_empty;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_apply;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_star_2_element;
  private final ReduceAction<TerminalEnum,ProductionEnum,VersionEnum> reduceexpr_id;

  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift62;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift16;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift55;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift73;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift53;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift50;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift30;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift6;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift44;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift29;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift11;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift40;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift3;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift14;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift32;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift61;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift22;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift59;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift8;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift5;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift93;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift46;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift24;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift26;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift13;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift96;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift10;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift69;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift80;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift87;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift20;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift23;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift90;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift2;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift28;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift9;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift17;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift34;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift19;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift76;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift72;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift57;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift48;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift64;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift7;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift81;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift67;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift42;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift38;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift79;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift4;
  private final ShiftAction<TerminalEnum,ProductionEnum,VersionEnum> shift36;


  private final ErrorAction<TerminalEnum,ProductionEnum,VersionEnum> error0;

  private final BranchAction<TerminalEnum,ProductionEnum,VersionEnum> branch0;


  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0ge_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0id_optional_4_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0init_metadata0reduceinit_star_6_rec;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0comma_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0expr_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0id_star_5_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0expr_star_2_metadata0reduceargs;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0_return_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0rpar_metadata0reduceexpr_method_call;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0lcurl_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0instr_star_0_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0lpar_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0expr_star_2_sub_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0eoi_metadata0reduceinstr_var_decl;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0semicolon_metadata0reduceeoi__semicolon;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0rcurl_metadata0reduceexpr_new;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0instr_metadata0reduceinstr_star_0_rec;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0_while_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0gt_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0block_metadata0reduceexpr_function_creation;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0function_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0_else_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0id_metadata0reduceid_optional_4_id;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0colon_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0init_star_6_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0block_metadata0reduceinstr_if;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0sub_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0id_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0lcurl_metadata0reduceinstr_star_1_empty;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0eoi_metadata0reduceinstr_return;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0init_metadata0reduceinit_star_6_element;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0block_metadata0reduceinstr_while;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0div_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0_if_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0block_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0id_metadata0reduceexpr_id;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0instr_star_1_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0instr_metadata0reduceinstr_star_1_rec;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0init_star_6_sub_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0eoi_metadata0reduceinstr_field_assign;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0eoi_metadata0reduceinstr_var_assign;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0eol_metadata0reduceeol_optional_3_eol;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0lt_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0rpar_metadata0reduceexpr_apply;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0mul_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0id_metadata0reduceid_star_5_rec;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0add_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0le_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0ne_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0__eof___metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0rem_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0dot_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0text_metadata0reduceexpr_text;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0rcurl_metadata0reduceblock;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0script_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0eoi_metadata0reduceinstr_expr;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0id_star_5_sub_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0eq_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0rpar_metadata0reduceexpr_parens;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0eol_optional_3_metadata0reduceinit;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0args_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0rpar_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0integer_metadata0reduceexpr_numeric;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0eol_metadata0reduceeoi__eol;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0assign_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0null_metadata0reduceinstr_star_0_empty;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0var_metadata0null;
  private StateMetadata<TerminalEnum,NonTerminalEnum,ProductionEnum,VersionEnum> metadata0id_metadata0reduceid_star_5_element;
}
