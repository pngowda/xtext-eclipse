package org.eclipse.xtext.idea.generator.parser.antlr;

import com.google.common.collect.Iterables;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Condition;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.UnorderedGroup;
import org.eclipse.xtext.generator.parser.antlr.AntlrOptions;
import org.eclipse.xtext.idea.generator.parser.antlr.DefaultAntlrGrammarGenerator;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xtext.generator.parser.antlr.AntlrGrammarGenUtil;

@SuppressWarnings("all")
public class AbstractAntlrGrammarWithActionsGenerator extends DefaultAntlrGrammarGenerator {
  @Override
  protected String compileInit(final AbstractRule it, final AntlrOptions options) {
    String _switchResult = null;
    boolean _matched = false;
    if (it instanceof ParserRule) {
      _matched=true;
      _switchResult = this.compileEntryInit(((ParserRule)it), options).toString();
    }
    if (!_matched) {
      _switchResult = super.compileInit(it, options);
    }
    return _switchResult;
  }
  
  protected CharSequence compileEntryInit(final ParserRule it, final AntlrOptions options) {
    StringConcatenation _builder = new StringConcatenation();
    {
      if ((it.isDefinesHiddenTokens() || this._grammarAccessExtensions.definesUnorderedGroups(it, options))) {
        _builder.append("@init {");
        _builder.newLine();
        _builder.append("\t");
        CharSequence _compileInitHiddenTokens = this.compileInitHiddenTokens(it, options);
        _builder.append(_compileInitHiddenTokens, "\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        CharSequence _compileInitUnorderedGroups = this.compileInitUnorderedGroups(it, options);
        _builder.append(_compileInitUnorderedGroups, "\t");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
      }
    }
    return _builder;
  }
  
  protected CharSequence _compileInitHiddenTokens(final AbstractRule it, final AntlrOptions options) {
    return "";
  }
  
  protected CharSequence _compileInitHiddenTokens(final ParserRule it, final AntlrOptions options) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isDefinesHiddenTokens = it.isDefinesHiddenTokens();
      if (_isDefinesHiddenTokens) {
        _builder.append("HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens(");
        {
          EList<AbstractRule> _hiddenTokens = it.getHiddenTokens();
          boolean _hasElements = false;
          for(final AbstractRule hidden : _hiddenTokens) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(", ", "");
            }
            _builder.append("\"");
            String _ruleName = this._grammarAccessExtensions.ruleName(hidden);
            _builder.append(_ruleName);
            _builder.append("\"");
          }
        }
        _builder.append(");");
      }
    }
    return _builder;
  }
  
  protected CharSequence _compileInitUnorderedGroups(final AbstractRule it, final AntlrOptions options) {
    return "";
  }
  
  protected CharSequence _compileInitUnorderedGroups(final ParserRule it, final AntlrOptions options) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _definesUnorderedGroups = this._grammarAccessExtensions.definesUnorderedGroups(it, options);
      if (_definesUnorderedGroups) {
        _builder.newLineIfNotEmpty();
        _builder.append("UnorderedGroupState myUnorderedGroupState = getUnorderedGroupHelper().snapShot(");
        _builder.newLine();
        {
          Iterable<UnorderedGroup> _filter = Iterables.<UnorderedGroup>filter(EcoreUtil2.eAllContentsAsList(it), UnorderedGroup.class);
          boolean _hasElements = false;
          for(final UnorderedGroup group : _filter) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(", ", "");
            }
            _builder.append("grammarAccess.");
            String _gaRuleElementAccessor = this._grammarAccess.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(group));
            _builder.append(_gaRuleElementAccessor);
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append(");");
      }
    }
    return _builder;
  }
  
  @Override
  protected String compileFinally(final AbstractRule it, final AntlrOptions options) {
    String _switchResult = null;
    boolean _matched = false;
    if (it instanceof ParserRule) {
      _matched=true;
      _switchResult = this.compileEntryFinally(((ParserRule)it), options).toString();
    }
    if (!_matched) {
      _switchResult = super.compileFinally(it, options);
    }
    return _switchResult;
  }
  
  protected CharSequence compileEntryFinally(final ParserRule it, final AntlrOptions options) {
    StringConcatenation _builder = new StringConcatenation();
    {
      if ((it.isDefinesHiddenTokens() || this._grammarAccessExtensions.definesUnorderedGroups(it, options))) {
        _builder.append("finally {");
        _builder.newLine();
        _builder.append("\t");
        CharSequence _compileRestoreHiddenTokens = this.compileRestoreHiddenTokens(it, options);
        _builder.append(_compileRestoreHiddenTokens, "\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        CharSequence _compileRestoreUnorderedGroups = this.compileRestoreUnorderedGroups(it, options);
        _builder.append(_compileRestoreUnorderedGroups, "\t");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
      }
    }
    return _builder;
  }
  
  protected CharSequence _compileRestoreHiddenTokens(final AbstractRule it, final AntlrOptions options) {
    return "";
  }
  
  protected CharSequence _compileRestoreHiddenTokens(final ParserRule it, final AntlrOptions options) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isDefinesHiddenTokens = it.isDefinesHiddenTokens();
      if (_isDefinesHiddenTokens) {
        _builder.append("myHiddenTokenState.restore();");
      }
    }
    return _builder;
  }
  
  protected CharSequence _compileRestoreUnorderedGroups(final AbstractRule it, final AntlrOptions options) {
    return "";
  }
  
  protected CharSequence _compileRestoreUnorderedGroups(final ParserRule it, final AntlrOptions options) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _definesUnorderedGroups = this._grammarAccessExtensions.definesUnorderedGroups(it, options);
      if (_definesUnorderedGroups) {
        _builder.append("myUnorderedGroupState.restore();");
      }
    }
    return _builder;
  }
  
  @Override
  protected String _dataTypeEbnf2(final Group it, final boolean supportActions) {
    String _xifexpression = null;
    Condition _guardCondition = it.getGuardCondition();
    boolean _tripleEquals = (_guardCondition == null);
    if (_tripleEquals) {
      _xifexpression = super._dataTypeEbnf2(it, supportActions);
    } else {
      String _guardConditionToAntlr = AntlrGrammarGenUtil.guardConditionToAntlr(it);
      String _plus = (_guardConditionToAntlr + "(");
      String __dataTypeEbnf2 = super._dataTypeEbnf2(it, supportActions);
      String _plus_1 = (_plus + __dataTypeEbnf2);
      _xifexpression = (_plus_1 + ")");
    }
    return _xifexpression;
  }
  
  @Override
  protected String _dataTypeEbnf2(final UnorderedGroup it, final boolean supportActions) {
    String _xifexpression = null;
    if (supportActions) {
      String _xblockexpression = null;
      {
        final Function1<AbstractElement, Boolean> _function = (AbstractElement it_1) -> {
          boolean _isOptionalCardinality = GrammarUtil.isOptionalCardinality(it_1);
          return Boolean.valueOf((!_isOptionalCardinality));
        };
        final int mandatoryContent = IterableExtensions.size(IterableExtensions.<AbstractElement>filter(it.getElements(), _function));
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("(");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("{ ");
        _builder.newLine();
        _builder.append("\t  ");
        _builder.append("getUnorderedGroupHelper().enter(grammarAccess.");
        String _gaRuleElementAccessor = this._grammarAccess.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it));
        _builder.append(_gaRuleElementAccessor, "\t  ");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("(");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("(");
        _builder.newLine();
        {
          Iterable<Pair<Integer, AbstractElement>> _indexed = IterableExtensions.<AbstractElement>indexed(it.getElements());
          boolean _hasElements = false;
          for(final Pair<Integer, AbstractElement> element : _indexed) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate("|", "");
            }
            _builder.append("(");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("{getUnorderedGroupHelper().canSelect(grammarAccess.");
            String _gaRuleElementAccessor_1 = this._grammarAccess.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it));
            _builder.append(_gaRuleElementAccessor_1, "\t");
            _builder.append(", ");
            Integer _key = element.getKey();
            _builder.append(_key, "\t");
            _builder.append(")}?=>(");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("{");
            _builder.newLine();
            _builder.append("\t\t\t");
            _builder.append("getUnorderedGroupHelper().select(grammarAccess.");
            String _gaRuleElementAccessor_2 = this._grammarAccess.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it));
            _builder.append(_gaRuleElementAccessor_2, "\t\t\t");
            _builder.append(", ");
            Integer _key_1 = element.getKey();
            _builder.append(_key_1, "\t\t\t");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("}");
            _builder.newLine();
            _builder.append("\t\t\t\t\t");
            _builder.append("({true}?=>(");
            String _dataTypeEbnf2 = this.dataTypeEbnf2(element.getValue(), supportActions);
            _builder.append(_dataTypeEbnf2, "\t\t\t\t\t");
            _builder.append("))");
            {
              boolean _isMultipleCardinality = GrammarUtil.isMultipleCardinality(element.getValue());
              if (_isMultipleCardinality) {
                _builder.append("+");
              }
            }
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("{ ");
            _builder.newLine();
            _builder.append("\t\t\t");
            _builder.append("getUnorderedGroupHelper().returnFromSelection(grammarAccess.");
            String _gaRuleElementAccessor_3 = this._grammarAccess.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it));
            _builder.append(_gaRuleElementAccessor_3, "\t\t\t");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("}");
            _builder.newLine();
            _builder.append("\t");
            _builder.append(")");
            _builder.newLine();
            _builder.append(")");
            _builder.newLine();
          }
        }
        _builder.append("\t\t");
        _builder.append(")");
        {
          if ((mandatoryContent != 0)) {
            _builder.append("+");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("{getUnorderedGroupHelper().canLeave(grammarAccess.");
            String _gaRuleElementAccessor_4 = this._grammarAccess.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it));
            _builder.append(_gaRuleElementAccessor_4, "\t\t");
            _builder.append(")}?");
          } else {
            _builder.append("*");
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(")");
        _builder.newLine();
        _builder.append(")");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("{ ");
        _builder.newLine();
        _builder.append("\t  ");
        _builder.append("getUnorderedGroupHelper().leave(grammarAccess.");
        String _gaRuleElementAccessor_5 = this._grammarAccess.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it));
        _builder.append(_gaRuleElementAccessor_5, "\t  ");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _xblockexpression = _builder.toString();
      }
      _xifexpression = _xblockexpression;
    } else {
      _xifexpression = super._dataTypeEbnf2(it, supportActions);
    }
    return _xifexpression;
  }
  
  @Override
  protected String _ebnf2(final Group it, final AntlrOptions options, final boolean supportActions) {
    String _xifexpression = null;
    Condition _guardCondition = it.getGuardCondition();
    boolean _tripleEquals = (_guardCondition == null);
    if (_tripleEquals) {
      _xifexpression = super._ebnf2(it, options, supportActions);
    } else {
      String _guardConditionToAntlr = AntlrGrammarGenUtil.guardConditionToAntlr(it);
      String _plus = (_guardConditionToAntlr + "(");
      String __ebnf2 = super._ebnf2(it, options, supportActions);
      String _plus_1 = (_plus + __ebnf2);
      _xifexpression = (_plus_1 + ")");
    }
    return _xifexpression;
  }
  
  @Override
  protected String _ebnf2(final UnorderedGroup it, final AntlrOptions options, final boolean supportActions) {
    String _xifexpression = null;
    if (supportActions) {
      String _xblockexpression = null;
      {
        final Function1<AbstractElement, Boolean> _function = (AbstractElement it_1) -> {
          boolean _isOptionalCardinality = GrammarUtil.isOptionalCardinality(it_1);
          return Boolean.valueOf((!_isOptionalCardinality));
        };
        final int mandatoryContent = IterableExtensions.size(IterableExtensions.<AbstractElement>filter(it.getElements(), _function));
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("(");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("{ ");
        _builder.newLine();
        _builder.append("\t  ");
        _builder.append("getUnorderedGroupHelper().enter(grammarAccess.");
        String _gaRuleElementAccessor = this._grammarAccess.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it));
        _builder.append(_gaRuleElementAccessor, "\t  ");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("(");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("(");
        _builder.newLine();
        {
          Iterable<Pair<Integer, AbstractElement>> _indexed = IterableExtensions.<AbstractElement>indexed(it.getElements());
          boolean _hasElements = false;
          for(final Pair<Integer, AbstractElement> element : _indexed) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate("|", "");
            }
            _builder.append("(");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("{getUnorderedGroupHelper().canSelect(grammarAccess.");
            String _gaRuleElementAccessor_1 = this._grammarAccess.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it));
            _builder.append(_gaRuleElementAccessor_1, "\t");
            _builder.append(", ");
            Integer _key = element.getKey();
            _builder.append(_key, "\t");
            _builder.append(")}?=>(");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("{");
            _builder.newLine();
            _builder.append("\t\t\t");
            _builder.append("getUnorderedGroupHelper().select(grammarAccess.");
            String _gaRuleElementAccessor_2 = this._grammarAccess.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it));
            _builder.append(_gaRuleElementAccessor_2, "\t\t\t");
            _builder.append(", ");
            Integer _key_1 = element.getKey();
            _builder.append(_key_1, "\t\t\t");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("}");
            _builder.newLine();
            _builder.append("\t\t\t\t\t");
            _builder.append("({true}?=>(");
            String _ebnf2 = this.ebnf2(element.getValue(), options, supportActions);
            _builder.append(_ebnf2, "\t\t\t\t\t");
            _builder.append("))");
            {
              boolean _isMultipleCardinality = GrammarUtil.isMultipleCardinality(element.getValue());
              if (_isMultipleCardinality) {
                _builder.append("+");
              }
            }
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("{ ");
            _builder.newLine();
            _builder.append("\t\t\t");
            _builder.append("getUnorderedGroupHelper().returnFromSelection(grammarAccess.");
            String _gaRuleElementAccessor_3 = this._grammarAccess.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it));
            _builder.append(_gaRuleElementAccessor_3, "\t\t\t");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("}");
            _builder.newLine();
            _builder.append("\t");
            _builder.append(")");
            _builder.newLine();
            _builder.append(")");
            _builder.newLine();
          }
        }
        _builder.append("\t\t");
        _builder.append(")");
        {
          if ((mandatoryContent != 0)) {
            _builder.append("+");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("{getUnorderedGroupHelper().canLeave(grammarAccess.");
            String _gaRuleElementAccessor_4 = this._grammarAccess.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it));
            _builder.append(_gaRuleElementAccessor_4, "\t\t");
            _builder.append(")}?");
          } else {
            _builder.append("*");
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(")");
        _builder.newLine();
        _builder.append(")");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("{ ");
        _builder.newLine();
        _builder.append("\t  ");
        _builder.append("getUnorderedGroupHelper().leave(grammarAccess.");
        String _gaRuleElementAccessor_5 = this._grammarAccess.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it));
        _builder.append(_gaRuleElementAccessor_5, "\t  ");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _xblockexpression = _builder.toString();
      }
      _xifexpression = _xblockexpression;
    } else {
      _xifexpression = super._ebnf2(it, options, supportActions);
    }
    return _xifexpression;
  }
  
  @Override
  protected String _ebnf2(final RuleCall it, final AntlrOptions options, final boolean supportActions) {
    String __ebnf2 = super._ebnf2(it, options, supportActions);
    String _argumentList = AntlrGrammarGenUtil.getArgumentList(it, this.isPassCurrentIntoFragment(), (!supportActions));
    return (__ebnf2 + _argumentList);
  }
  
  @Override
  protected String _dataTypeEbnf2(final RuleCall it, final boolean supportActions) {
    String __dataTypeEbnf2 = super._dataTypeEbnf2(it, supportActions);
    String _argumentList = AntlrGrammarGenUtil.getArgumentList(it, this.isPassCurrentIntoFragment(), (!supportActions));
    return (__dataTypeEbnf2 + _argumentList);
  }
  
  @Override
  protected String crossrefEbnf(final AbstractRule it, final RuleCall call, final CrossReference ref, final boolean supportActions) {
    String _crossrefEbnf = super.crossrefEbnf(it, call, ref, supportActions);
    String _argumentList = AntlrGrammarGenUtil.getArgumentList(call, this.isPassCurrentIntoFragment(), (!supportActions));
    return (_crossrefEbnf + _argumentList);
  }
  
  @Override
  protected String _assignmentEbnf(final RuleCall it, final Assignment assignment, final AntlrOptions options, final boolean supportActions) {
    String __assignmentEbnf = super._assignmentEbnf(it, assignment, options, supportActions);
    String _argumentList = AntlrGrammarGenUtil.getArgumentList(it, this.isPassCurrentIntoFragment(), (!supportActions));
    return (__assignmentEbnf + _argumentList);
  }
  
  protected boolean isPassCurrentIntoFragment() {
    return false;
  }
  
  protected String getCurrentType() {
    return "EObject";
  }
  
  protected CharSequence compileInitHiddenTokens(final AbstractRule it, final AntlrOptions options) {
    if (it instanceof ParserRule) {
      return _compileInitHiddenTokens((ParserRule)it, options);
    } else if (it != null) {
      return _compileInitHiddenTokens(it, options);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, options).toString());
    }
  }
  
  protected CharSequence compileInitUnorderedGroups(final AbstractRule it, final AntlrOptions options) {
    if (it instanceof ParserRule) {
      return _compileInitUnorderedGroups((ParserRule)it, options);
    } else if (it != null) {
      return _compileInitUnorderedGroups(it, options);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, options).toString());
    }
  }
  
  protected CharSequence compileRestoreHiddenTokens(final AbstractRule it, final AntlrOptions options) {
    if (it instanceof ParserRule) {
      return _compileRestoreHiddenTokens((ParserRule)it, options);
    } else if (it != null) {
      return _compileRestoreHiddenTokens(it, options);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, options).toString());
    }
  }
  
  protected CharSequence compileRestoreUnorderedGroups(final AbstractRule it, final AntlrOptions options) {
    if (it instanceof ParserRule) {
      return _compileRestoreUnorderedGroups((ParserRule)it, options);
    } else if (it != null) {
      return _compileRestoreUnorderedGroups(it, options);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, options).toString());
    }
  }
}
